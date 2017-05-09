/******  do_data.c                                                            
                                                                              
   Processes the actual input data stream (the compressed data) and           
   writes out the decompressed data.  The output file is padded with          
   NULLs out to a multiple of 2880 bytes.                                     
                                                                              
   Modified 3/29/90 by AW3 to preserve input buffer length sent to            
   decomp - fixes bug when carrying over pixels from one read to the          
   next                                                                       
                                                                              
******/                                                                       
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
#include <stdio.h>                                                            
#include <stdlib.h>                                                           
#include <string.h>                                                           
#include <io.h>                                                               
                                                                              
/* External stuff from dec.c -- used everywhere */                            
extern     char    inbfr[],        /* the file input buffer */                
                   _end[];         /* the end-of-header buffer */             
                                                                              
                                                                              
void   do_data(FILE *infile, FILE *outfile, long *file_len)                   
{                                                                             
   long    total_read,             /* Total number of data bytes read */      
           total_left,             /* Number of bytes left to be read */      
           total_written,          /* Number of data bytes written out */     
           bytlen,                 /* Number of bytes fed to decomp */        
           num_decomp,             /* Number of pixels from decomp */         
           n_left,                 /* Number of "hanging" bytes from decomp */
           onepercent,             /* used for printing status */             
           j,                      /* used for printing status */             
           n_rec;                  /* Number of FITS records to read */       
                                                                              
   int     ipixel,                 /* Starting pixel from decomp */           
           n_read,                 /* Number of bytes read into inbfr */      
           rec_counter,            /* loop counter */                         
           num_written,            /* Number of bytes written out */          
           read_in,                /* Number of bytes to read at a time */    
		   in_len,                 /* Length of input going to DECOMP */            
		   *temp_buffer;           /* decompression buffer */                       
                                                                              
/* Allocate space to the decompression buffer */                              
   temp_buffer = (int *)malloc(3000 * sizeof(int) );                          
                                                                              
/* Initialize a bunch of stuff */                                             
   read_in       = FITS_SIZE;                                                 
   total_left    = *file_len;                                                 
   onepercent    = total_left / 100;                                          
                                                                              
   /* Calculate number of FITS records to read in */                          
   n_rec         = total_left / read_in;                                      
   if ( (total_left % read_in) != 0) n_rec++;                                 
                                                                              
   total_read    = 0L;                                                        
   n_left        = 0L;                                                        
   num_decomp    = 0L;                                                        
   total_written = 0L;                                                        
   j             = 1;                                                         
   ipixel        = 0;                                                         
                                                                              
/* Let 'em know we're alive, and have parsed the extension header */          
   clrscr();                                                                  
   printf("Compressed file length= %ld bytes\n\n",*file_len);                 
   printf("File decompression started......\n\n");                            
   printf("Percent Decompressed:\n\n");                                       
                                                                              
/* Work our way through the data, decompressing as we go */                   
   for (rec_counter = 0; rec_counter < n_rec; rec_counter++)                  
   {                                                                          
       memset( &inbfr[n_left], NULL, FITS_SIZE);   /* blank the input */      
                                                                              
       /* Read the data at offset n_left to preserve left-over bytes */       
       n_read = fread( &inbfr[n_left], sizeof(char), read_in, infile);        
       total_read = total_read + (long)n_read;                                
                                                                              
       /*                                                                     
          Tell decomp how many bytes to work on, watching for the end         
          of the data, then do the decompression.                             
       */                                                                     
	   in_len = (long)min( (long)n_read, total_left) + n_left;                   
       bytlen = in_len ;     /* save this guy since decomp will change it */  
	   decomp( inbfr, &bytlen, &ipixel, temp_buffer, &num_decomp);               
	   n_left = (long)n_read + n_left - bytlen;    /* anything left over? */     
                                                                              
       /* write out whatever is decompressed and update total */              
       num_written   = fwrite( temp_buffer, sizeof(int), num_decomp, outfile);
                                                                              
       /* Keep track of total bytes written */                                
       total_written = total_written + (long)(num_written * 2);               
       total_left = total_left - n_read;                                      
                                                                              
       /* set up for next call - save left-over bytes, if any */              
       if (n_left == 1)                                                       
		   inbfr[0] = inbfr[in_len - n_left];                                       
       if (n_left == 2)                                                       
       {                                                                      
		   inbfr[0] = inbfr[in_len - n_left];                                       
		   inbfr[1] = inbfr[in_len - n_left + 1];                                   
       }                                                                      
                                                                              
/* keep track of % decompressed as we go along */                             
                                                                              
       if (total_read >= (j * onepercent) )                                   
       {                                                                      
           printf("%7d",j);                                                   
           j++;                                                               
           if((j % 10) == 1) printf("\n");                                    
       }                                                                      
   }                                                                          
                                                                              
/* pad the file with nulls out to a multiple of 2880 bytes */                 
   num_decomp = FITS_SIZE - ( total_written % FITS_SIZE);                     
   memset( temp_buffer, NULL, num_decomp );                                   
   num_written = fwrite( temp_buffer, sizeof(char), num_decomp, outfile);     
   free( temp_buffer );                                                       
}                                                                             
