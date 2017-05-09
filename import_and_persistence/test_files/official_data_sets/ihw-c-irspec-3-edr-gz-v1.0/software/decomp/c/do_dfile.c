/******  do_dfile.c                                                           
                                                                              
   Asks for the data file name, if necessary, and opens the file              
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
#include <stdio.h>                                                            
#include <string.h>                                                           
                                                                              
FILE   *do_datafile(int argc, char *argv[], char *linebfr)                    
{                                                                             
   FILE    *datafile;                                                         
   char    *ptr;                                                              
                                                                              
/**************************************************************************   
                                                                              
    Process the OUTPUT file name and open the file                            
                                                                              
*/                                                                            
                                                                              
   if (argc > 3)                                                              
   {                                                                          
       strcpy( linebfr, argv[3]);                                             
   }                                                                          
   else                                                                       
   {                                                                          
       ptr = strtok( linebfr, ".");                                           
       if (ptr != NULL)                                                       
       {                                                                      
           strcat( ptr, ".IMQ");                                              
           strcpy( linebfr, ptr);                                             
       }                                                                      
       else                                                                   
       {                                                                      
           printf( "Enter the name for the FITS data file: ");                
           gets(linebfr);                                                     
           if ( !strlen(linebfr) )                                            
           {                                                                  
               printf( "You must enter a file name!\n" );                     
               exit();                                                        
           }                                                                  
       }                                                                      
   }                                                                          
                                                                              
/* Open the text file */                                                      
   datafile = fopen( linebfr, "rb" );                                         
   if ( !datafile )                                                           
   {                                                                          
       printf( "Can't open output file!\n" );                                 
       exit();                                                                
   }                                                                          
   return(datafile);                                                          
}                                                                             
