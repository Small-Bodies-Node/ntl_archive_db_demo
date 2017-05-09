/******  putkey.c                                                             
                                                                              
   Puts logical keyword "f_key" into byte offset 80*keynum of buffer          
   "outrec", followed by logical value "f_val" in column 30 and comment       
   "f_comm" after that.                                                       
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
                                                                              
#include <stdio.h>                                                            
#include <string.h>                                                           
                                                                              
extern char      *strresize( char *string, int newlen );                      
                                                                              
void   put_lkey( char *outrec, int keynum, char *f_key, char *f_val,          
                 char *f_comm)                                                
{                                                                             
   int     offset;                 /* Byte offset from start of header */     
   char    linebfr[81];            /* Scratch string */                       
                                                                              
   linebfr[0] = NULL;              /* Make linebfr a string */                
   strcpy( linebfr, f_key );       /* Put the keyword in place */             
   strresize( linebfr, 29 );        /* Pad out the right number of bytes */   
   linebfr[8] = '=';               /* Put in the equals sign */               
   strcat( linebfr, f_val );       /* Add on the value */                     
   strcat( linebfr, " " );         /* Put a blank after the value */          
   strcat( linebfr, f_comm);       /* Add on the comment string */            
   strresize( linebfr, 80 );        /* Make sure it's 80 bytes long */        
                                                                              
   offset = keynum * 80 ;          /* Figure out where this line goes */      
                                                                              
   /* Copy the keyword into the FITS buffer */                                
   memcpy( outrec + offset, linebfr, 80) ;                                    
}                                                                             
