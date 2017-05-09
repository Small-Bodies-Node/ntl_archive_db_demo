/******  xtension.c                                                           
                                                                              
   Process extension header.  While in extension header, get file             
   length, image dimensions and start writing output header.  Someday,        
   this routine should copy all the keywords after COMPRES1 over to the       
   output header.                                                             
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
#include <stdio.h>                                                            
#include <stdlib.h>                                                           
#include <string.h>                                                           
                                                                              
extern     char    inbfr[],                                                   
                   _end[];                                                    
                                                                              
/* Function templates */                                                      
extern void   put_lkey( char *outrec, int keynum, char *f_key, char *f_val,   
                 char *f_comm);                                               
extern char   *strresize( char *string, int newlen );                         
extern char   *strreplace(char *string, int start, int num, char *repstr );   
                                                                              
                                                                              
void   xtension(FILE *infile, FILE *outfile, long *file_len)                  
{                                                                             
   char    *ptr,                       /* Scratch string */                   
           linebfr[81],                /* Scratch string */                   
           outbfr[ FITS_SIZE ],        /* genuine FITS-sized buffer */        
           *key_str;                   /* Holds keyword */                    
                                                                              
   int     counter,                    /* Counter for FITS records */         
           nkey,                       /* Counter for output keywords */      
           buf_size,                   /* output buffer len */                
           eoh;                        /* End of header flag */               
                                                                              
/* Initialization */                                                          
   eoh = FALSE;                                                               
   counter = 0;                                                               
   linebfr[80] = NULL;                                                        
                                                                              
/* Start reading header records */                                            
   while ( (fgets( inbfr, FITS_SIZE+1, infile) != NULL) && (eoh == FALSE) )   
   {                                                                          
       counter++;                                                             
       if (counter == 1)                                                      
       {                                                                      
           /* Is EXTEND there? */                                             
           if ((ptr = strstr( inbfr, "XTENSION= ")) != NULL)                  
           {                                                                  
           /*                                                                 
               Now, we're in the first record.  We need to get the file       
               file length and the number of axes for the output image        
               after checking that the value of XTENSION really is            
               COMPRESS.                                                      
                                                                              
               After we extract those things, we need to start building       
               the output buffer.                                             
           */                                                                 
               strncpy( linebfr, ptr, TEXT_SIZE);                             
               key_str = strtok( linebfr, "'") ;                              
               key_str = strtok( NULL, "'") ;                                 
               if (strcmp( key_str, "COMPRESS") == 0)                         
               {                                                              
               /*                                                             
                   This is a legitimate COMPRESSed image (finally).           
                   Start getting useful stuff from the header, then           
                   copy remaining lines to output header.                     
               */                                                             
                   ptr = strstr( inbfr, "NAXIS1  = ");                        
                   strncpy( linebfr, ptr, TEXT_SIZE);                         
                   key_str = strtok( linebfr, "= ");                          
                   key_str = strtok( NULL, "= ");                             
                   *file_len = atol( key_str );                               
                                                                              
               /*                                                             
                   Make the initial part of the first output header           
                   (i.e., put in SIMPLE = T)                                  
               */                                                             
                   outbfr[0] = NULL;                   /* blank the buffer */ 
                   strresize( outbfr, FITS_SIZE );                            
                   put_lkey( outbfr, 0, "SIMPLE", "T", "/ VALID FITS FORMAT");
                   nkey = 1;                                                  
                                                                              
               /*                                                             
                   Now copy logical values to the output header,              
                   renaming keywords as we go                                 
                                                                              
                   LBITPIX -> BITPIX                                          
               */                                                             
                   ptr = strstr( inbfr, "LBITPIX = ");                        
                   strncpy( linebfr, ptr, TEXT_SIZE);                         
                   strreplace( linebfr, 0, 7, "BITPIX ");                     
                   strncpy( &outbfr[80*nkey], linebfr, TEXT_SIZE);            
                   nkey++;                                                    
                                                                              
               /*  LAXIS -> NAXIS */                                          
                   ptr = strstr( inbfr, "LAXIS   = ");                        
                   strncpy( linebfr, ptr, TEXT_SIZE);                         
                   linebfr[0] = 'N';                                          
                   strncpy( &outbfr[80*nkey], linebfr, TEXT_SIZE);            
                   nkey++;                                                    
                                                                              
               /*  LAXIS1 -> NAXIS1 */                                        
                   ptr = strstr( inbfr, "LAXIS1  = ");                        
                   strncpy( linebfr, ptr, TEXT_SIZE);                         
                   linebfr[0] = 'N';                                          
                   strncpy( &outbfr[80*nkey], linebfr, TEXT_SIZE);            
                   nkey++;                                                    
                                                                              
               /*  LAXIS2 -> NAXIS2 */                                        
                   ptr = strstr( inbfr, "LAXIS2  = ");                        
                   strncpy( linebfr, ptr, TEXT_SIZE);                         
                   linebfr[0] = 'N';                                          
                   strncpy( &outbfr[80*nkey], linebfr, TEXT_SIZE);            
                   nkey++;                                                    
                                                                              
               /*  END */                                                     
                   strncpy( &outbfr[80*nkey], _end, TEXT_SIZE);               
                   nkey++;                                                    
                                                                              
               }                                                              
               else                                                           
               {                                                              
                   printf( "FITS extension not COMPRESS\n");                  
                   exit(0);                                                   
               }                                                              
           }                                                                  
           else                                        /* Not there, quit */  
           {                                                                  
               printf( "No XTENSION keyword found - processing terminated\n");
               exit(1);                                                       
           }                                                                  
       }                                                                      
       if ((ptr = strstr( inbfr, _end)) != NULL)       /* Is END there? */    
       {                                                                      
           eoh = TRUE ;                                /* set flag if yes */  
           break ;                                     /* and quit */         
       }                                                                      
   }                                                                          
                                                                              
   /* Now, write this guy out. */                                             
   fwrite( outbfr, sizeof(char), FITS_SIZE, outfile);                         
}                                                                             
