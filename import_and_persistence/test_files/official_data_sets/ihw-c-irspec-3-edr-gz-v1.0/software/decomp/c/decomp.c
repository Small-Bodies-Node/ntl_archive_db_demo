/* decomp.c                                                                   
                                                                              
   RECONSTRUCTS AN IMAGE COMPRESSED BY PREVIOUS PIXEL. THE INPUT BYTE         
   STREAM IS IN THE BYTE ARRAY POINTED TO BY INPTR  AND THE OUTPUT IS         
   PASSED BACK IN THE INTEGER*2 ARRAY POINTED TO BY OUTPTR.                   
                                                                              
   THE COMPRESSION SCHEME IS GIVEN IN THE IDL PROCEDURE PREVPIX.PRO           
                                                                              
   ARGUMENT LIST:                                                             
       INPTR   - INPUT:  POINTER TO  BYTE ARRAY OF FIRST DIFFERENCES          
       BYTLEN  - INPUT:  INTEGER*4 NUMBER OF BYTES TO DECOMPRESS              
                 OUTPUT: ACTUAL NUMBER OF BYTES DECOMPRESSED                  
       IPIXEL  - INPUT:  INTEGER*2 STARTING PIXEL VALUE                       
                 OUTPUT: LAST PIXEL VALUE CONVERTED                           
       OUTPTR  - OUTPUT: POINTER TO INTEGER*2 ARRAY OF DECOMPRESSED           
                         PIXELS                                               
       OFFSET  - OUTPUT: INTEGER*4 ACTUAL NUMBER OF PIXELS DECOMPRESSED       
                                                                              
   WRITTEN 12/88 BY A. WARNOCK, ST SYSTEMS CORP.                              
   CONVERTED TO VAX C BY B. PFARR, ST SYSTEMS CORP., 3/89                     
   Converted to Turbo C v2.0 by A. Warnock, ST Systems Corp., 1/90            
   Bug fix in "for" comparison, A. Warnock, 1/90                              
                                                                              
*/                                                                            
                                                                              
/* function declarations  */                                                  
void bytswp( short int *ptr1, short int *ptr2 );                              
                                                                              
void decomp( char *inptr, long int *bytlen, short int *ipixel,                
             short int outptr[], long int *offset)                            
{                                                                             
   int     instep,                                                            
           pxleft,                                                            
           i;                                                                 
                                                                              
   static  unsigned    char    bigflg =  255;                                 
   short   int         *image;                                                
   char    *bytarr;                                                           
                                                                              
   /* save calling sequence pointers */                                       
   bytarr = inptr;                                                            
   image = outptr;                                                            
                                                                              
   /* SET INITIAL POINTERS */                                                 
   /* INSTEP POINTS INTO INPUT ARRAY */                                       
   /* OFFSET POINTS INTO IMAGE ARRAY */                                       
                                                                              
   for (i=1, instep=1, *offset=0;                                             
       (i == *bytlen) || (instep <= *bytlen);                                 
       i++, ++*offset)                                                        
   {                                                                          
                                                                              
       /* DO WE HAVE A 16-bit NUMBER FLAG AND BOTH THE NEEDED BYTES? */       
       if (*bytarr == bigflg)                                                 
       {                                                                      
           pxleft = *bytlen - instep;                                         
                                                                              
           /* NOT ENOUGH BYTES LEFT TO DECOMPRESS THE LAST PIXEL */           
           if (pxleft < 2)                                                    
           {                                                                  
               if (instep ==  *bytlen) *bytlen = *bytlen-1;                   
               goto l900;                                                     
           }                                                                  
                                                                              
           /* RESET THE PIXEL VALUE WITH A NEW 16-BIT NUMBER */               
           bytswp( ++bytarr, ipixel);                                         
           bytarr = bytarr + 2;                                               
           instep = instep + 3;                                               
       }                                                                      
       else                                                                   
       {                                                                      
                                                                              
           /* CORRECT BIAS FOR MACHINES READING SIGNED LOGICAL*1 */           
           if (*bytarr < 0)                                                   
           {                                                                  
               *ipixel = *ipixel + *bytarr++ + 129;                           
           }                                                                  
           else                                                               
           {                                                                  
                                                                              
           /* CORRECT BIAS FOR POSITIVE BYTES AND UNSIGNED LOGICAL*1 */       
               *ipixel = *ipixel + *bytarr++ - 127;                           
           }                                                                  
           instep = instep + 1;                                               
       }                                                                      
                                                                              
       /* EITHER WAY, STORE AWAY THE NEW PIXEL VALUE AND GO BACK */           
       bytswp( ipixel, image++);                                              
   }                                                                          
                                                                              
        /* RETURN WITH UPDATED COUNT */                                       
                                                                              
l900:   if (instep < *bytlen) *bytlen = instep - 1;                           
}                                                                             
                                                                              
/* SUBROUTINE BYTSWP                                                          
                                                                              
   SWAPS THE BYTE ORDER INTO INTEGER*2 NUMBER                                 
                                                                              
*/                                                                            
                                                                              
void bytswp( short int *ptr1, short int *ptr2 )                               
{                                                                             
    char temp, *cptr1, *cptr2;                                                
                                                                              
   /* COPY NUMBER FROM FIRST POINTER TO SECOND POINTER. */                    
   *ptr2  = *ptr1;                                                            
                                                                              
   /* SWAP BYTES AT POINTER 2 */                                              
   cptr1  = ptr2;                                                             
   cptr2  = cptr1 + 1;                                                        
   temp   = *cptr1;                                                           
   *cptr1 = *cptr2;                                                           
   *cptr2 = temp;                                                             
}                                                                             
