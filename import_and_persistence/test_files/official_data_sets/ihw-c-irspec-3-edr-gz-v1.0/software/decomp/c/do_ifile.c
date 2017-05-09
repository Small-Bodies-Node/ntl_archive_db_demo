/******  do_ifile.c                                                           
                                                                              
   Asks for the input file name, if necessary, and opens the file             
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
#include <stdio.h>                                                            
#include <string.h>                                                           
                                                                              
FILE   *do_infile(int argc, char *argv[], char *linebfr)                      
{                                                                             
   FILE    *infile;                                                           
                                                                              
/**************************************************************************   
                                                                              
    Process the INPUT header file name and open the file                      
                                                                              
*/                                                                            
                                                                              
   if ( argc > 1 )                                                            
   {                                                                          
       strcpy( linebfr, argv[1]) ;                                            
   }                                                                          
   else                                                                       
   {                                                                          
       printf( "Enter the name of the FITS header file: ");                   
       gets(linebfr);                                                         
       if ( !strlen(linebfr) )                                                
       {                                                                      
           printf( "You must enter a file name!\n" );                         
           exit();                                                            
       }                                                                      
   }                                                                          
                                                                              
/* Open the header file */                                                    
   infile = fopen( linebfr, "rb" );                                           
   if ( !infile )                                                             
   {                                                                          
       printf( "Can't open input file!\n" );                                  
       exit();                                                                
   }                                                                          
   return(infile);                                                            
}                                                                             
