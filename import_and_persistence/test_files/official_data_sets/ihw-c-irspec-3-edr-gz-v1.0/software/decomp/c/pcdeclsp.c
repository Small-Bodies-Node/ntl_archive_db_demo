/******    PROGRAM PCDECLSP.C                                                 
                                                                              
                                                                              
This program reads in a compressed FITS image, decompresses it and writes it  
out as a legitimate FITS byte stream.  It reads and writes the FITS records as
streams of 2880 bytes.                                                        
                                                                              
NOTE:  The FITS header and associated data are assumed to be in SEPARATE      
       files.                                                                 
                                                                              
If no file name is specified on the command line, the program will prompt for 
one.  If only one name is given on the command line, it is assumed to be the  
input file, and the output file will be given the same file name except that  
the extension will be set to '$$$'.  If two names are given on the command    
line, the first is taken as the input file, the second as the output file.    
The data file is assumed to have the extension .IMQ, but this can be changed  
by specifying a third file name on the command line.                          
                                                                              
EXAMPLES:                                                                     
>pcdeclsp                                                                     
      - asks for header file name, reads data from *.IMQ,                     
        makes *.$$$ as output                                                 
>pcdeclsp lspn0094.hdr                                                        
      - reads data from LSPN0094.IMQ, makes LSPN0094.$$$ as output            
>pcdeclsp lspn0094.hdr d:lspn0094.fit                                         
      - reads data from LSPN0094.IMQ, makes LSPN0094.FIT on drive D:          
>pcdeclsp lspn0094.hdr d:lspn0094.fit lspn0094.dat                            
      - reads data from LSPN0094.DAT, makes LSPN0094.FIT on drive D:          
                                                                              
Version 1.0 written 1/90 by                                                   
           A. Warnock                                                         
           ST Systems Corp.                                                   
           NASA/Goddard Space Flight Center                                   
           Code 681                                                           
           Greenbelt, MD 20771                                                
           (301)286-3965                                                      
           SPAN - STARS::WARNOCK                                              
                  6168::WARNOCK                                               
           Internet - warnock@stars.gsfc.nasa.gov                             
                      warnock@[128.183.84.7]                                  
                                                                              
Version 1.1, 3/90 by AW3 - saves correct input buffer length in do_data       
                           to fix a bug in carrying a new 16-bit value        
                           from the end of one input buffer to the            
                           beginning of the next.                             
                                                                              
Version 1.2, 10/90 by AW3 - uses public domain routines to replace previous   
                            shareware routines to improve portability.  The   
                            routines strreplc.c and strresiz.c come from      
                            the TCCLIB version 2.0, written and released      
                            into the public domain by                         
                                                                              
                            Chris Collins                                     
                            15 Faculty Row                                    
                            Greenville, SC 29609                              
                                                                              
Written for Turbo C++ v1.0 from Borland Int'l., but uses no C++ extensions.   
Will compile and link under the tiny model to generate a .COM file.  See      
MAKEFILE for details.                                                         
                                                                              
*****/                                                                        
                                                                              
#include <stdio.h>                                                            
#include <stdlib.h>                                                           
#include <string.h>                                                           
#include <conio.h>                                                            
                                                                              
#define NULL 0                                                                
#define TRUE 'T'                                                              
#define FALSE 'F'                                                             
#define FITS_SIZE 2880                                                        
#define TEXT_SIZE 80                                                          
                                                                              
extern FILE   *do_infile( int argc, char *argv[], char *linebfr);             
extern FILE   *do_datafile( int argc, char *argv[], char *linebfr);           
extern FILE   *do_outfile( int argc, char *argv[], char *linebfr);            
extern void   header( FILE *infile);                                          
extern void   xtension( FILE *infile, FILE *outfile, long *file_len);         
extern void   do_data( FILE *infile, FILE *outfile, long *file_len);          
extern void   decomp( char *inptr, long int *bytlen, short int *ipixel,       
               short int outbfr[], long int *offset);                         
extern char   *strresize( char *string, int newlen );                         
                                                                              
/* Global declarations */                                                     
   char   inbfr[3000],                         /* input buffer */             
          _end[TEXT_SIZE + 1];                 /* end-of-header string */     
                                                                              
main(int argc, char *argv[])                                                  
{                                                                             
   FILE    *infile,                    /* Input file */                       
           *outfile;                   /* Output file */                      
                                                                              
   char    linebfr[TEXT_SIZE + 1],     /* holds the command line */           
           tempbfr[TEXT_SIZE + 1];     /* scratch buffer */                   
                                                                              
   long    file_len;                   /* data length from FITS header */     
                                                                              
/* Get the file names and open the INPUT and OUTPUT files. */                 
   infile  = do_infile(  argc, argv, linebfr);                                
   strcpy( tempbfr, linebfr );                                                
   outfile = do_outfile( argc, argv, linebfr);                                
                                                                              
/* Initialize some string stuff first */                                      
   strcpy( _end, "END" );                                                     
   strresize( _end, TEXT_SIZE) ;                                              
   inbfr[ FITS_SIZE ] = NULL;                                                 
                                                                              
/* Process the primary header */                                              
   header( infile );                                                          
                                                                              
/* Process the extension header and write the output header */                
   xtension( infile, outfile, &file_len);                                     
                                                                              
/* Decompress the data stream and write the output file */                    
   fclose( infile );                                                          
   infile = do_datafile( argc, argv, tempbfr);                                
   do_data( infile, outfile, &file_len);                                      
                                                                              
/* Clean up after ourselves */                                                
   fclose( infile );                                                          
   fclose( outfile );                                                         
   printf( "\nAll Done!\n" );                                                 
                                                                              
/* And exit */                                                                
   return(0);                                                                 
}                                                                             
