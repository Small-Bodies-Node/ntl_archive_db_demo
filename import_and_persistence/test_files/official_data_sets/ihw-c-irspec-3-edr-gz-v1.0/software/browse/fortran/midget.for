      PROGRAM MIDGET                                                          
C *** "MIDGET" PRODUCES "BROWSE" IMAGES WITH A MAXIMUM DIMENSION OF           
C     256 PIXELS FROM "LARGE SCALE PHENOMENON NETWORK" (LSPN) IMAGES OF       
C     ANY SIZE.                                                               
C                                                                             
C     THIS PROGRAM WAS DEVELOPED BY                                           
C             JOHN M. BOGERT III                                              
C             LABORATORY FOR ASTRONOMY AND SOLAR PHYSICS                      
C             GODDARD SPACE FLIGHT CENTER                                     
C             GREENBELT, MD 20771                                             
C     THE PROGRAM WAS RUN ON THE SPACE AND EARTH SCIENCES' IBM 3081           
C     COMPUTER.                                                               
C                                                                             
      CHARACTER RECORD*80,HNAME*44,BNAME*44,VOID*80/' '/,HEADER*80(36)        
     &  ,HIST1*40/'HISTORY   BROWSE IMAGE CREATED'/,DATE*9                    
     &  ,HIST2*80/'HISTORY   BROWSE IMAGE DIMENSION SCALE FACTOR ='/          
     &  ,HIST3*80/'HISTORY   BROWSE IMAGE DENSITY SCALE FACTOR = 4'/          
     +  ,DISK*6/'USERDA'/,DEV*5/'SYSDA'/,TRK*3/'TRK'/,BLANK*8/'BLANK'/        
     +  ,LRECL*5/'LRECL'/,TOUSER*5,PREF*5/' '/                                
     2  ,HKEY*8(8)/'BITPIX','NAXIS1','NAXIS2','CRPIX1','CRPIX2',              
     3  'CDELT1','CDELT2','END'/,BITS*8,END*8,KWORD*8,PARM*20,DESC*50         
     4  ,DWARF*1(65536),Z00*1/Z00/                                            
      LOGICAL*4 FOUND                                                         
      REAL*4  DATA(7)                                                         
      INTEGER*4 LDBLK/23040/,LDREC/512/,LHBLK/15440/,LHREC/80/                
     2  ,MDY(14),KSTAT(5)/5*0/,KPIX4(6400)                                    
      INTEGER*2  KPIX(12800)                                                  
C *** SEVERAL INITIALIZED PARAMETERS ARE RE-COMPUTED OR UNUSED IN THE         
C     BODY OF THE PROGRAM BECAUSE SYSTEM FACILITIES PROVED UNRELIABLE         
C     OR INCORRECT IN OBTAINING THEIR VALUES.                                 
      EQUIVALENCE (KWORD,RECORD(1:8)),(PARM,RECORD(11:30))                    
     &  ,(DESC,RECORD(31:80))                                                 
     2  ,(NBITS,DATA(1)),(NPIX,DATA(2)),(NLINE,DATA(3))                       
     3  ,(BITS,HKEY(1)),(END,HKEY(8)),(DATE,HIST1(32:40))                     
     4  ,(KPIX4(1),KPIX(1))                                                   
      CALL DATIMX (MDY)                                                       
      WRITE(DATE,'(2(I2.2,1H/),I2.2)') MDY(6),MDY(7),MDY(14)                  
      GO TO 10                                                                
   8  FORMAT(/' *** ',A,' IS NOT A VALID "FITS" HEADER FILE.')                
C *** THE INPUT RECORD DOES NOT BEGIN WITH A VALID "FITS" HEADER FILE.        
   9  WRITE(06,8) HNAME(1:18)                                                 
C *** READ AN INPUT IMAGE HEADER FILE                                         
C     OUTPUT IS WRITTEN TO THE OWNER OF THE INPUT HEADER UNLESS               
C     A PREFIX "TOUSER" IS INCLUDED ON THE IOPUT RECORD.                      
C *** "NDEN" IS THE POWER OF TWO OF THE DENSITY SCALE FACTOR                  
C     AND IS SET TO 2 (FACTOR = 4) IF NOT SPECIFIED                           
  10  READ(05,'(A44,1X,A5,I3)',END=900) HNAME,TOUSER,NDEN                     
      NH = INDEX(HNAME,'.HHH')+3                                              
      IF(NH.LT.9) GO TO 9                                                     
      IF(TOUSER(1:1).NE.' ') PREF = TOUSER                                    
         BNAME = HNAME(1:NH)                                                  
         HNAME = BNAME                                                        
      WRITE(06,15) HNAME,DATE                                                 
  15  FORMAT('1 IMAGE HEADER: ',2A / )                                        
      INQUIRE (FILE='/'//HNAME,EXIST=FOUND)                                   
      IF(.NOT.FOUND) GO TO 90                                                 
      IH1 = MAX(INDEX(HNAME,'.')+1,6)                                         
      BNAME(IH1:IH1) = 'B'                                                    
      IF(PREF.EQ.' ') PREF = BNAME(1:IH1-2)                                   
      BNAME(1:IH1-2) = PREF                                                   
      CALL FILEINF (IERR,'DEVICE',DEV)                                        
      OPEN (UNIT=10,FILE='/'//HNAME,STATUS='OLD',                             
     +  FORM='FORMATTED',IOSTAT=IERR,ERR=90)                                  
      NPIX = 0                                                                
      NS = 0                                                                  
      K = 0                                                                   
      K1 = 4                                                                  
C *** READ A HEADER FILE RECORD                                               
  20  READ(10,'(A)',END=80) RECORD                                            
      K = K+1                                                                 
      IF(KWORD.NE.BITS) GO TO 22                                              
C *** THE "BITPIX" RECORD WILL BE CHANGED FOR THE OUTPUT FILES                
      WRITE(06,37) K,RECORD                                                   
      READ(PARM,*) NBITS                                                      
      WRITE(PARM,'(18X,2H 8)')                                                
      WRITE(DESC,'(''  / 1-BYTE INTEGER'')')                                  
      GO TO 26                                                                
  22  IF(KWORD(1:5).NE.'NAXIS') GO TO 26                                      
      IF(KWORD(6:6).EQ.' ') GO TO 26                                          
      WRITE(06,37) K,RECORD                                                   
      IF(KWORD(6:6).EQ.'2') GO TO 30                                          
C *** GET THE "NAXIS1" DIMENSION                                              
      K1 = K                                                                  
      READ(PARM,*) NPIX                                                       
  26  HEADER(K) = RECORD                                                      
      IF(K.LT.36) GO TO 20                                                    
      GO TO 80                                                                
  30  IF(NPIX.LT.1) GO TO 80                                                  
C *** GET THE "NAXIS2" DIMENSION                                              
      READ(PARM,*) NLINE                                                      
C *** COMPUTE SAMPLE RATE FOR RESTRICTING LARGEST DIMENSION TO                
C     256 PIXELS                                                              
      NS = MAX((NPIX+255)/256,(NLINE+255)/256)                                
      NAX2 = (NLINE+NS-1)/NS                                                  
      WRITE(PARM,'(17X,I3)') NAX2                                             
      NAX1 = (NPIX+NS-1)/NS                                                   
      WRITE(HEADER(K1)(11:30),'(17X,I3)') NAX1                                
      HEADER(K) = RECORD                                                      
      WRITE(06,'(/4H /// /)' )                                                
      INQUIRE (FILE='/'//BNAME,EXIST=FOUND)                                   
      IF(FOUND) GO TO 92                                                      
      CALL FILEINF (IERR,'DEVICE',DEV,'VOLSER',DISK,TRK,1,'SECOND',1,         
     +  'RECFM','FB','LRECL',LHREC,'BLKSIZE',LHBLK)                           
      OPEN (UNIT=20,FILE='/'//BNAME,STATUS='NEW',                             
     +  FORM='FORMATTED',IOSTAT=IERR,ERR=92)                                  
      DO 35 I=1,K                                                             
      WRITE(06,36) I,HEADER(I)                                                
      WRITE(20,'(A)') HEADER(I)                                               
  35  CONTINUE                                                                
  36  FORMAT(8X,'(',I3.3,') ',A )                                             
  37  FORMAT(4X,'--> (',I3.3,') ',A )                                         
  40  READ(10,'(A)',END=50) RECORD                                            
      IF(KWORD.EQ.END) GO TO 50                                               
      IF(KWORD.EQ.BLANK) GO TO 46                                             
C *** SEE IF THIS RECORD CONTAINS A SCALE FACTOR THAT NEEDS CHANGING          
      K = K+1                                                                 
      DO 42 J=4,7                                                             
      IF(KWORD.EQ.HKEY(J)) GO TO 44                                           
  42  CONTINUE                                                                
      GO TO 45                                                                
  44  WRITE(06,37) K,RECORD                                                   
C *** READ THE DATUM AND CORRECT FOR THE NEW SAMPLE RATE                      
      READ(PARM,*) DATA(J)                                                    
      IF(J.LT.6) DATA(J) = DATA(J) / NS + 0.5                                 
      IF(J.GE.6) DATA(J) = DATA(J) * NS                                       
      WRITE(PARM,'(3X,E17.7)') DATA(J)                                        
  45  WRITE(20,'(A)') RECORD                                                  
      WRITE(06,36) K,RECORD                                                   
      GO TO 40                                                                
  46  WRITE(06,37) K+1,RECORD                                                 
C *** THE "BLANK" KEYWORD RECORD IS EXCLUDED FROM "BROWSE" IMAGES             
      GO TO 40                                                                
  50  NBP = (NBITS+7)/8                                                       
      NDEN = MAX(2**NDEN,4)                                                   
C *** WRITE THE "BROWSE" HISTORY RECORDS                                      
      WRITE(HIST2(48:50),'(I3)') NS                                           
      WRITE(HIST3(46:56),'(I11)') NDEN                                        
      WRITE(20,'(A)') HIST1                                                   
      WRITE(20,'(A)') HIST2                                                   
      WRITE(20,'(A)') HIST3                                                   
      WRITE(20,'(A)') END                                                     
      WRITE(06,36) K+1,HIST1                                                  
      WRITE(06,36) K+2,HIST2                                                  
      WRITE(06,36) K+3,HIST3                                                  
      K = K+4                                                                 
      WRITE(06,36) K,END                                                      
      CLOSE (10)                                                              
      CLOSE (20)                                                              
      GO TO 100                                                               
C *** THE FOLLOWING ARE TRAPS FOR ERRORS OCCURRING WHILE ATTEMPTING           
C     TO PROCESS "LSPN" OR "BROWSE" HEADER FILES.                             
  80  WRITE(06,81) HNAME                                                      
      CLOSE (10)                                                              
      GO TO 10                                                                
  81  FORMAT(' *** UNABLE TO LOCATE SAMPLE COUNTS IN HEADER: ',A/             
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
  90  WRITE(06,91) HNAME                                                      
      CLOSE (10)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      GO TO 10                                                                
  91  FORMAT(' *** UNABLE TO ACCESS INPUT HEADER FILE: ' ,A /                 
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
  92  WRITE(06,93) BNAME                                                      
      CLOSE (10)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      KSTAT(5) = KSTAT(5)+1                                                   
      GO TO 10                                                                
  93  FORMAT(' *** UNABLE TO OPEN OUTPUT HEADER FILE: ',A /                   
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
 100  CONTINUE                                                                
      L2 = 1+NS*(NAX2-1)                                                      
      N2 = 1+NS*(NAX1-1)                                                      
      N1 = 1                                                                  
      HNAME(NH:NH) = 'D'                                                      
      BNAME(NH:NH) = 'D'                                                      
      WRITE(06,105) NPIX,NLINE,NBP,N2,L2                                      
 105  FORMAT(//I6,1HX,I4.4,I2,'-BYTE PIXELS:'/                                
     &         '  LAST INPUT PIXEL =',I5 /                                    
     &         '  LAST INPUT  LINE =',I5)                                     
      INQUIRE (FILE='/'//HNAME,EXIST=FOUND)                                   
      IF(.NOT.FOUND) GO TO 190                                                
      CALL FILEINF (IERR,'DEVICE',DEV)                                        
      OPEN (UNIT=11,FILE='/'//HNAME,STATUS='OLD',                             
     +  FORM='UNFORMATTED',IOSTAT=IERR,ERR=190)                               
      IT = 0                                                                  
      KMIN = 32767                                                            
      KMAX = -32767                                                           
      LP = LDREC/2                                                            
      LRM1 = LP-1                                                             
      LPIX = NBP*NPIX/2                                                       
C *** READ EACH LINE OF "LSPN" DATA:                                          
      DO 150 K=1,L2                                                           
C *** RECORDS ARE EXPECTED TO BE IN MULTIPLES OF 256 PIXELS, OR               
C     LESS THAN 512 BYTES:                                                    
      DO 120 J=N1,LPIX,LP                                                     
      L = J+LRM1                                                              
 120  READ(11,END=195) (KPIX(I),I=J,L)                                        
      IF(NS.GT.1.AND.MOD(K,NS).NE.1) GO TO 140                                
C *** THIS IS A "BROWSE" SAMPLE LINE                                          
C     COMPUTE THE BYTE-DENSITY VALUE OF EACH NS'TH INTEGER*2 PIXEL FOR        
C     THE "BROWSE" OUTPUT DATA IN "DWARF"                                     
      DO 130 I=1,N2,NS                                                        
      IT = IT+1                                                               
      IF(NBP.GT.2) THEN                                                       
          IX = KPIX4(I)                                                       
      ELSE                                                                    
          IX = KPIX(I)                                                        
      ENDIF                                                                   
      KMIN = MIN(KMIN,IX)                                                     
      KMAX = MAX(KMAX,IX)                                                     
      MITE = MIN(MAX(IX/NDEN,0),255)                                          
 130  DWARF(IT) = CHAR(MITE)                                                  
 140  N1 = 1                                                                  
      IF(L-NPIX) 195, 150, 141                                                
 141  IF(K.GE.L2) GO TO 155                                                   
C *** DELETE THE PROCESSED PIXELS AND SHIFT THE REMAINDER                     
C     TO THE BEGINNING OF THE BUFFER                                          
      L = L-NPIX                                                              
      DO 145 I=1,L                                                            
 145  KPIX(I) = KPIX(I+NPIX)                                                  
      N1 = L+1                                                                
 150  CONTINUE                                                                
 155  CLOSE (11)                                                              
      INQUIRE (FILE='/'//BNAME,EXIST=FOUND)                                   
      IF(FOUND) GO TO 192                                                     
      CALL FILEINF (IERR,'DEVICE',DEV,'VOLSER',DISK,TRK,2,'SECOND',2,         
     +  'RECFM','FB','LRECL',LDREC,'BLKSIZE',LDBLK)                           
      OPEN (UNIT=21,FILE='/'//BNAME,STATUS='NEW',                             
     +  FORM='UNFORMATTED',IOSTAT=IERR,ERR=192)                               
      LRM1 = LDREC-1                                                          
C *** OUTPUT THE "BROWSE" DATA CONTAINED IN "DWARF"                           
      DO 160 I=1,IT,LDREC                                                     
      J = MIN(I+LRM1,IT)                                                      
C *** THE NEXT 4 LINES SHOULD BE ENABLED IF THE FILE DEFINITION               
C     DOES NOT "PAD" THE FINAL "FITS" RECORD WITH ZEROES TO 2880 BYTES:       
C     IN WHICH CASE, J SHOULD BE EXACTLY "I+LRM1", I.E.,                      
C     DWARF(I:J) = ONE "FITS" RECORD.                                         
C     IF(J.GT.IT)  THEN                                                       
C         DO 159 K=IT+1,J                                                     
C159      DWARF(K) = Z00                                                      
C     ENDIF                                                                   
 160  WRITE(21) (DWARF(K),K=I,J)                                              
C *** COMPUTE THE DATA SPACE AND CLOSE FILES.                                 
      LRB = LDBLK/LDREC                                                       
      K = (IT+LRM1)/LDREC                                                     
      J = (K+LRB-1)/LRB                                                       
      IX = NAX1*NAX2                                                          
      WRITE(06,161) BNAME,NAX1,NAX2,IX,J,K,KMIN,KMAX                          
      CLOSE (21)                                                              
 161  FORMAT(//' OUTPUT FILE ',A //                                           
     &  7X,I3,1HX,I3.3,' =',I6.5,' BYTES =',I2,' DISK BLOCKS =',              
     &  I5,' VAX RECORDS.'//                                                  
     &  6X,'PRE-CONVERSION DATA RANGE =',I6,' TO',I10.4)                      
      KSTAT(1) = KSTAT(1)+1                                                   
      KSTAT(2) = KSTAT(2)+J                                                   
      KSTAT(3) = KSTAT(3)+K                                                   
      GO TO 10                                                                
C *** THE FOLLOWING ARE TRAPS FOR ERRORS OCCURRING WHILE ATTEMPTING           
C     TO PROCESS "LSPN" OR "BROWSE" DATA FILES                                
 190  WRITE(06,191) HNAME                                                     
      CLOSE (11)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      GO TO 10                                                                
 191  FORMAT(' *** UNABLE TO ACCESS INPUT IMAGE FILE: ',A /                   
     &  6X,'PROCESSING OF THIS IMAGE ABORTED WITH HEADER CATALOGED.')         
 192  WRITE(06,193) BNAME                                                     
      CLOSE (11)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      KSTAT(5) = KSTAT(5)+1                                                   
      GO TO 10                                                                
 193  FORMAT(' *** UNABLE TO OPEN OUTPUT IMAGE FILE: ',A /                    
     &  6X,'PROCESSING OF THIS IMAGE ABORTED WITH HEADER CATALOGED.')         
 195  WRITE(06,196) K,L                                                       
      CLOSE (11)                                                              
      GO TO 10                                                                
 196  FORMAT(' *** DATA ERROR: INPUT LINE ',I4,' ENDED AT SAMPLE ',I4/        
     &  6X,'PROCESSING OF THIS IMAGE ABORTED WITH HEADER CATALOGED.')         
 900  CONTINUE                                                                
C *** DISPLAY THE CLOSING STATISTICS:                                         
      WRITE(06,905) (KSTAT(I),I=1,3)                                          
      IF(KSTAT(4).GT.0) WRITE(06,906) KSTAT(4)                                
      IF(KSTAT(5).GT.0) WRITE(06,907) KSTAT(5)                                
 905  FORMAT(1H1/' STATISTICS:'//I5,' IMAGES COMPLETED:'//                    
     &  '   TOTAL OF ',I5,' DISK BLOCKS, ',I6,' VAX RECORDS, WRITTEN.')       
 906  FORMAT(/8X,I4,' IMAGES COULD NOT BE PROCESSED.')                        
 907  FORMAT(/8X,I4,' OUTPUT FILES COULD NOT BE ALLOCATED.')                  
      STOP                                                                    
      END                                                                     
