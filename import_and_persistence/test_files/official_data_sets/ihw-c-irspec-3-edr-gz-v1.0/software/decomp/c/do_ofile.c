/******  do_ofile.c                                                           
                                                                              
   Asks for the output file name, if necessary, and opens the file            
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
#include <stdio.h>                                                            
#include <string.h>                                                           
                                                                              
FILE   *do_outfile(int argc, char *argv[], char *linebfr)                     
{                                                                             
   FILE    *outfile;                                                          
   char    *ptr;                                                              
                                                                              
/**************************************************************************   
                                                                              
    Process the OUTPUT file name and open the file                            
                                                                              
*/                                                                            
                                                                              
   if (argc > 2)                                                              
   {                                                                          
       strcpy( linebfr, argv[2]);                                             
   }                                                                          
   else                                                                       
   {                                                                          
       ptr = strtok( linebfr, ".");                                           
       if (ptr != NULL)                                                       
       {                                                                      
           strcat( ptr, ".$$$");                                              
           strcpy( linebfr, ptr);                                             
       }                                                                      
       else                                                                   
       {                                                                      
           printf( "Enter the name for the output file: ");                   
           gets(linebfr);                                                     
           if ( !strlen(linebfr) )                                            
           {                                                                  
               printf( "You must enter a file name!\n" );                     
               exit();                                                        
           }                                                                  
       }                                                                      
   }                                                                          
                                                                              
/* Open the text file */                                                      
   outfile = fopen( linebfr, "wb" );                                          
   if ( !outfile )                                                            
   {                                                                          
       printf( "Can't open output file!\n" );                                 
       exit();                                                                
   }                                                                          
   return(outfile);                                                           
}                                                                             
