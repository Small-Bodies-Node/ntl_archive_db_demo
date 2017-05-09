      SUBROUTINE DECOMP( BYTARR, BYTLEN, IPIXEL, IMAGE, OFFSET)               
C                                                                             
C-----RECONSTRUCTS AN IMAGE COMPRESSED BY PREVIOUS PIXEL. THE INPUT           
C      BYTE STREAM IS IN THE BYTE ARRAY BYTARR AND THE OUTPUT IS              
C      PASSED BACK IN THE INTEGER*2 ARRAY IMAGE.                              
C                                                                             
C-----THE COMPRESSION SCHEME IS GIVEN IN THE IDL PROCEDURE PREVPIX.PRO        
C                                                                             
C-----ARGUMENT LIST:                                                          
C      BYTARR      - INPUT:  BYTE ARRAY OF FIRST DIFFERENCES                  
C      BYTLEN      - INPUT:  INTEGER*4 NUMBER OF BYTES TO DECOMPRESS          
C                    OUTPUT: ACTUAL NUMBER OF BYTES DECOMPRESSED              
C      IPIXEL      - INPUT:  INTEGER*2 STARTING PIXEL VALUE                   
C                    OUTPUT: LAST PIXEL VALUE CONVERTED                       
C      IMAGE       - OUTPUT: INTEGER*2 ARRAY OF DECOMPRESSED PIXELS           
C      OFFSET      - OUTPUT: INTEGER*4 ACTUAL NUMBER OF PIXELS DECOMPRESSED   
C                                                                             
C-----WRITTEN 12/88 BY A. WARNOCK, ST SYSTEMS CORP.                           
C                                                                             
      LOGICAL*1 BYTARR, BIGFLAG                                               
      INTEGER*2 IMAGE, IPIXEL                                                 
      INTEGER*4 BYTLEN, OFFSET, INSTEP, PXLEFT                                
C                                                                             
      DIMENSION BYTARR( 1 ), IMAGE( 1 )                                       
C                                                                             
      DATA BIGFLG / -1 /                                                      
C                                                                             
C-----SET INITIAL POINTERS                                                    
C      INSTEP POINTS INTO BYTARR                                              
C      OFFSET POINTS INTO IMAGE                                               
C                                                                             
      INSTEP = 1                                                              
      OFFSET = 0                                                              
      DO 10 I = 1, BYTLEN                                                     
         OFFSET = OFFSET + 1                                                  
C                                                                             
C-----DO WE HAVE A 16-BIT NUMBER FLAG AND BOTH THE NEEDED BYTES?              
C                                                                             
         IF (BYTARR( INSTEP ) .EQ. BIGFLG) THEN                               
            PXLEFT = BYTLEN - INSTEP                                          
C                                                                             
C-----NOT ENOUGHT BYTES LEFT TO DECOMPRESS THE LAST PIXEL                     
C                                                                             
            IF (PXLEFT .LT. 2) THEN                                           
                   IF (INSTEP .EQ. BYTLEN) BYTLEN = BYTLEN-1                  
               OFFSET = OFFSET - 1                                            
               GO TO 900                                                      
            ENDIF                                                             
C                                                                             
C-----RESET THE PIXEL VALUE WITH A NEW 16-BIT NUMBER                          
C                                                                             
            CALL BYTSWP( BYTARR( INSTEP + 1 ), IPIXEL)                        
            INSTEP = INSTEP + 3                                               
         ELSE                                                                 
C                                                                             
C-----CORRECT BIAS FOR MACHINES READING SIGNED LOGICAL*1                      
C                                                                             
            IF (BYTARR( INSTEP ) .LT. 0) THEN                                 
               IPIXEL = IPIXEL + BYTARR( INSTEP ) + 129                       
            ELSE                                                              
C                                                                             
C-----CORRECT BIAS FOR POSITIVE BYTES AND UNSIGNED LOGICAL*1                  
C                                                                             
               IPIXEL = IPIXEL + BYTARR( INSTEP ) - 127                       
            ENDIF                                                             
              INSTEP      = INSTEP + 1                                        
           ENDIF                                                              
C                                                                             
C-----EITHER WAY, STORE AWAY THE NEW PIXEL VALUE AND GO BACK                  
C                                                                             
           IMAGE( OFFSET ) = IPIXEL                                           
         IF (INSTEP .GT. BYTLEN) GO TO 900                                    
 10      CONTINUE                                                             
C                                                                             
C-----RETURN WITH UPDATED COUNT                                               
C                                                                             
 900      IF (INSTEP .LT. BYTLEN) BYTLEN = INSTEP - 1                         
      RETURN                                                                  
      END                                                                     
C****************************************************************             
C                                                                             
C      SUBROUTINE BYTSWP                                                      
C                                                                             
C****************************************************************             
C                                                                             
      SUBROUTINE BYTSWP( BYTIN, NUMBER )                                      
C                                                                             
C-----SWAPS THE BYTE ORDER INTO INTEGER*2 NUMBER                              
C                                                                             
      BYTE BSWAP, TEMP, BYTIN( 1 )                                            
      INTEGER*2 NUMBER, ISWAP                                                 
C                                                                             
      DIMENSION BSWAP( 2 )                                                    
      EQUIVALENCE (ISWAP, BSWAP(1) )                                          
C                                                                             
C-----PUT NUMBER INTO THE BYTE ARRAY AND SWAP THE BYTE ORDER                  
C                                                                             
      BSWAP( 2 ) = BYTIN( 1 )                                                 
      BSWAP( 1 ) = BYTIN( 2 )                                                 
      NUMBER = ISWAP                                                          
C                                                                             
C-----SEND THE BYTE-SWAPPED NUMBER BACK                                       
C                                                                             
      RETURN                                                                  
      END                                                                     
