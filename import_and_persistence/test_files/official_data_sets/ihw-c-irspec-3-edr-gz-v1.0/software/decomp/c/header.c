/******  header.c                                                             
                                                                              
   Processes the initial FITS header on the compressed image. Actually,       
   does nothing but bypass all of the records there since everything          
   relevant is in the extension header.  The input file is left pointing      
   at the start of the extension header.                                      
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
#include <stdio.h>                                                            
#include <string.h>                                                           
                                                                              
extern     char    inbfr[],                                                   
                   _end[];                                                    
                                                                              
                                                                              
void   header(FILE *infile)                                                   
{                                                                             
   char    *ptr;                                                              
   int     eoh;                                                               
                                                                              
/************************************************************************     
                                                                              
   Start reading the primary FITS header until the keyword END is found.      
   If 36 keywords are written without finding END, another 2880 byte          
   record is read from the input file.  Nothing from the primary header is    
   copied to the output file.                                                 
                                                                              
*/                                                                            
   eoh = FALSE;                                                               
   while ( (fgets( inbfr, FITS_SIZE+1, infile) != NULL) && (eoh == FALSE) )   
   {                                                                          
       if ((ptr = strstr( inbfr, "EXTEND  =")) != NULL) /* Is EXTEND there? */
       {                                                                      
           if (ptr[29] == FALSE)                        /* Is it FALSE? */    
           {                                                                  
               printf( "EXTEND = F found -- processing terminated\n");        
               exit(0);                                                       
           }                                                                  
       }                                                                      
       else                                             /* Not there, quit */ 
       {                                                                      
           printf( "EXTEND keyword not found -- processing terminated\n");    
           exit(1);                                                           
       }                                                                      
                                                                              
       if ((ptr = strstr( ptr, _end)) != NULL)          /* Is END there? */   
       {                                                                      
           eoh = TRUE ;                                 /* set flag if yes */ 
           break ;                                         /* and quit */     
       }                                                                      
   }                                                                          
}                                                                             
