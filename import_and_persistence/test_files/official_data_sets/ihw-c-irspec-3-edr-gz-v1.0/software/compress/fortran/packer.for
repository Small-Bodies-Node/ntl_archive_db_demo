      PROGRAM PACKER                                                          
C *** "PACKER" PRODUCES "PREVPIXEL" COMPRESSED IMAGES                         
C     FROM "FITS/LSPN" IMAGE FILES                                            
C                                                                             
C     THIS PROGRAM WAS DEVELOPED BY                                           
C             JOHN M. BOGERT III                                              
C             LABORATORY FOR ASTRONOMY AND SOLAR PHYSICS                      
C             GODDARD SPACE FLIGHT CENTER                                     
C             GREENBELT, MD 20771                                             
C     THE PROGRAM WAS RUN ON THE SPACE AND EARTH SCIENCES' IBM 3081           
C     COMPUTER.                                                               
C                                                                             
      CHARACTER RECORD*80,HNAME*44,CNAME*44,VOID*80/' '/,HEADER*80(360)       
     1/'SIMPLE  =                    T  / BASIC FITS FORMAT',                 
     2 'BITPIX  =                    8  / 1-BYTE DATA ONLY',                  
     3 'NAXIS   =                    0  / EXTENSION ONLY, NO DATA',           
     4 'EXTEND  =                    T  / EXTENSIONS MAY BE PRESENT',         
     5 'HISTORY   CREATED FOR FITS EXTENSIONS BY IBM:','END',                 
     &  30*' ',                                                               
     1 'XTENSION= ''COMPRESS''            / DATA IS A COMPRESSED IMAGE',      
     2 'BITPIX  =                    8  / 1 BYTE DATA STREAM',                
     3 'NAXIS   =                    1  / DATA IS A BYTE STREAM',             
     4 'NAXIS1  =              XXXXXXX  / LENGTH OF BYTE STREAM',             
     5 'PCOUNT  =                    0  / NO PARAMETERS PRECEDING DATA',      
     6 'GCOUNT  =                    1  / ONLY ONE GROUP OF DATA',            
     7 'COMPRES1= ''PREVPIXEL''           / FIRST COMPRESSION SCHEME',        
     8 'LBITPIX','LAXIS','LASIS1','LAXIS2',                                   
     L 'COMMENT','COMMENT   THE FOLLOWING KEYWORDS WERE COPIED FROM THE       
     MORIGINAL HEADER',                                                       
     &  311*' '/,DATE*9,TIME*12,PARM*20,DESC*50,TOUSER*5,PREF*5/' '/          
     +  ,DISK*6/'USERDA'/,DEV*5/'SYSDA'/,TRK*3/'TRK'/                         
     +  ,LRECL*5/'LRECL'/,BLANK*8/'BLANK'/                                    
     2  ,BITS*8/'BITPIX'/,LBITS*8/'LBITPIX'/,NAXIS*5/'NAXIS'/                 
     3  ,END*8/'END'/,KWORD*8,KEPT*2                                          
     &  ,CRUSH*1(6400),COLON*1/':'/,Z00*1/Z00/,ZFF*1/ZFF/                     
      LOGICAL*4 FOUND                                                         
      INTEGER*4 LDBLK/23040/,LDREC/512/,LHBLK/23040/,LHREC/80/                
     2  ,LCBLK/23040/,LCREC/2880/,MDY(14),KSTAT(5)/5*0/                       
      INTEGER*2  KPIX(768),KEEP                                               
C *** SEVERAL INITIALIZED PARAMETERS ARE RE-COMPUTED OR UNUSED IN THE         
C     BODY OF THE PROGRAM BECAUSE SYSTEM FACILITIES PROVED UNRELIABLE         
C     OR INCORRECT IN OBTAINING THEIR VALUES.                                 
      EQUIVALENCE (KWORD,RECORD(1:8)),(PARM,RECORD(11:30))                    
     3  ,(DATE,HEADER(5)(48:56)),(TIME,HEADER(5)(59:70)),(KEEP,KEPT)          
      LC1 = LCREC-1                                                           
      GO TO 10                                                                
   8  FORMAT(/' *** ',A,' IS NOT A VALID "FITS" HEADER FILE.')                
C *** THE INPUT IS NOT A VALID "FITS" HEADER FILE NAME                        
   9  WRITE(06,8) HNAME(1:18)                                                 
C *** READ THE INPUT HEADER FILE NAME;                                        
C     OUTPUT IS DIRECTED TO THE OWNER OF THE INPUT HEADER FILE                
C     UNLESS THE "TOUSER" PREFIX IS INCLUDED ON THE INPUT RECORD              
  10  READ(05,'(A44,1X,A5)',END=900) HNAME,TOUSER                             
      NH = INDEX(HNAME,'.HHH')+3                                              
      IF(NH.LT.9) GO TO 9                                                     
         CNAME = HNAME(1:NH)                                                  
         HNAME = CNAME                                                        
      CALL DATIMX (MDY)                                                       
      WRITE(HEADER(5)(48:70),'(2(I2.2,1H/),I2.2,3X,2(I2.2,1H:),I2.2)')        
     + MDY(6),MDY(7),MDY(14),MDY(5),MDY(4),MDY(3)                             
      WRITE(06,15) HNAME,DATE,TIME                                            
  15  FORMAT('1 IMAGE FILE: ',3A )                                            
      INQUIRE (FILE='/'//HNAME,EXIST=FOUND)                                   
      IF(.NOT.FOUND) GO TO 90                                                 
      IH1 = MAX(INDEX(HNAME,'.')+1,6)                                         
      CNAME(IH1:IH1) = 'C'                                                    
      IF(TOUSER.NE.' ') PREF = TOUSER                                         
      IF(PREF.EQ.' ') PREF = CNAME(1:IH1-2)                                   
      CNAME(1:IH1-2) = PREF                                                   
      CALL FILEINF (IERR,'DEVICE',DEV)                                        
      OPEN (UNIT=10,FILE='/'//HNAME,STATUS='OLD',                             
     +  FORM='FORMATTED',IOSTAT=IERR,ERR=90)                                  
      K = 43                                                                  
      NPIX = 0                                                                
      NLINE = 0                                                               
C *** THE "BITPIX" KEYWORD IS REPLACED IN THE COMPRESSED IMAGE AND            
C     THE ORIGINAL HEADER BEGUN IN RECORD 43 OF THE COMPRESSED HEADER         
      READ(10,'(A)',END=40) HEADER(1)                                         
  20  READ(10,'(A)',END=40) RECORD                                            
      K = K+1                                                                 
      IF(KWORD.EQ.BITS) KWORD = LBITS                                         
      IF(KWORD(1:5).NE.NAXIS) GO TO 26                                        
C *** READ THE "LSPN" DIMENSIONS                                              
      IF(KWORD(6:6).EQ.'1') READ(PARM,*) NPIX                                 
      IF(KWORD(6:6).EQ.'2') READ(PARM,*) NLINE                                
C *** DATA MODIFIED BY THE COMPRESSED IMAGE CHARACTERISTICS HAVE              
C     THEIR KEYWORDS PREFIXED WITH "L" (LOGICAL) TO AVOID AMBIGUITY           
      KWORD(1:1) = 'L'                                                        
  26  HEADER(K) = RECORD                                                      
      IF(KWORD.EQ.END) GO TO 50                                               
      IF(K.EQ.47) K = 49                                                      
      IF(K.LT.360) GO TO 20                                                   
  36  FORMAT(4X,'(',I3.3,') ',A )                                             
  40  K = K+1                                                                 
      HEADER(K) = END                                                         
  50  CLOSE (10)                                                              
      KH = K                                                                  
      IF(NPIX.GT.0.AND.NLINE.GT.0) GO TO 100                                  
C *** THE FOLLOWING ARE TRAPS FOR ERRORS OCCURRING WHILE ATTEMPTING           
C     TO PROCESS "LSPN" AND "COMPRESS" HEADER FILES                           
      WRITE(06,81) HNAME                                                      
      GO TO 10                                                                
  81  FORMAT('  *** UNABLE TO LOCATE SAMPLE COUNTS IN HEADER: ',A/            
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
  90  WRITE(06,91) HNAME                                                      
      CLOSE (10)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      GO TO 10                                                                
  91  FORMAT(' *** UNABLE TO ACCESS INPUT HEADER FILE: ' ,A /                 
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
  92  WRITE(06,93) CNAME                                                      
      KSTAT(4) = KSTAT(4)+1                                                   
      KSTAT(5) = KSTAT(5)+1                                                   
      GO TO 10                                                                
  93  FORMAT(' *** UNABLE TO OPEN OUTPUT HEADER FILE: ',A /                   
     &  6X,'PROCESSING OF THIS IMAGE ABORTED WITH DATA FILE WRITTEN.')        
 100  CONTINUE                                                                
      L2 = NPIX*NLINE                                                         
      HNAME(NH:NH) = 'D'                                                      
      CNAME(NH:NH) = 'D'                                                      
      INQUIRE (FILE='/'//HNAME,EXIST=FOUND)                                   
      IF(.NOT.FOUND) GO TO 190                                                
      CALL FILEINF (IERR,'DEVICE',DEV)                                        
      OPEN (UNIT=11,FILE='/'//HNAME,STATUS='OLD',                             
     +  FORM='UNFORMATTED',IOSTAT=IERR,ERR=190)                               
      LRB = LDREC/2                                                           
C *** GET THE FIRST "LSPN" DATA BLOCK                                         
      READ(11,END=190) (KPIX(I),I=1,LRB)                                      
C     CALL SWITCH (KPIX,KPIX,LDREC)                                           
C *** SET PIXEL TEST TO FORCE PIXEL(1) TO BE A REFERENCE PIXEL                
      LAST = KPIX(1)+999                                                      
      L1 = LRB+1                                                              
      LP = L2+1                                                               
      NREF = 0                                                                
      WRITE(06,105) CNAME,NPIX,NLINE,L2                                       
 105  FORMAT(/' OUTPUT IMAGE: ',A //                                          
     &  8X,'SAMPLES =',I5,'*',I4.4,' =',I9.8,' PIXELS')                       
      INQUIRE (FILE='/'//CNAME,EXIST=FOUND)                                   
      IF(FOUND) GO TO 192                                                     
      CALL FILEINF (IERR,'DEVICE',DEV,'VOLSER',DISK,TRK,50,'SECOND',50,       
     +  'RECFM','FB','LRECL',LCREC,'BLKSIZE',LCBLK)                           
      OPEN (UNIT=21,FILE='/'//CNAME,STATUS='NEW',                             
     +  FORM='UNFORMATTED',IOSTAT=IERR,ERR=192)                               
      IT = 0                                                                  
      NOUT = 0                                                                
C *** PROCESS THE FIRST DATA BLOCK                                            
      DO 115 I=1,LRB                                                          
      KEEP = KPIX(I)-LAST+127                                                 
      IF(KEEP.GE.0.AND.KEEP.LT.255) GO TO 110                                 
C *** THIS IS A REFERENCE PIXEL, +- >127 FROM LAST PIXEL VALUE;               
C     STORE 255 IN THE NEXT OUTPUT BYTE OF "CRUSH"                            
C     AND PUT THE PIXEL VALUE IN THE FOLLOWING TWO BYTES                      
      KEEP = KPIX(I)                                                          
      NREF = NREF+1                                                           
      IF(NREF.LT.6) WRITE(06,109) NREF,I,KEEP                                 
 109  FORMAT(/8X,'REFERENCE',I3,',  SAMPLE',I4,' =',I6)                       
      CRUSH(IT+1) = ZFF                                                       
      IT = IT+2                                                               
      CRUSH(IT) = KEPT(1:1)                                                   
 110  IT = IT+1                                                               
C *** "KEPT(2:2)" STORES THE DIFFERENCE TO +-127, OR                          
C     THE SECOND BYTE OF A REFERENCE PIXEL                                    
      CRUSH(IT) = KEPT(2:2)                                                   
      LAST = KPIX(I)                                                          
 115  CONTINUE                                                                
C *** READ THE "LSPN" DATA FILE, BLOCKS 2 TO END OF DATA:                     
      DO 150 K=L1,L2,LRB                                                      
      READ(11,END=195) (KPIX(I),I=1,LRB)                                      
C     CALL SWITCH (KPIX,KPIX,LDREC)                                           
      L = MIN(LRB,LP-K)                                                       
      DO 130 I=1,L                                                            
      KEEP = KPIX(I)-LAST+127                                                 
      IF(KEEP.GE.0.AND.KEEP.LT.255) GO TO 120                                 
C *** THIS IS A REFERENCE PIXEL, +- >127 FROM LAST PIXEL VALUE;               
C     STORE 255 IN THE NEXT OUTPUT BYTE OF "CRUSH"                            
C     AND PUT THE PIXEL VALUE IN THE FOLLOWING TWO BYTES                      
      KEEP = KPIX(I)                                                          
      NREF = NREF+1                                                           
      CRUSH(IT+1) = ZFF                                                       
      IT = IT+2                                                               
      CRUSH(IT) = KEPT(1:1)                                                   
 120  IT = IT+1                                                               
C *** "KEPT(2:2)" STORES THE DIFFERENCE TO +-127, OR                          
C     THE SECOND BYTE OF A REFERENCE PIXEL                                    
      CRUSH(IT) = KEPT(2:2)                                                   
      LAST = KPIX(I)                                                          
 130  CONTINUE                                                                
      IF(IT.LT.LCREC) GO TO 150                                               
C *** WRITE ALL COMPLETE RECORDS CONTAINED IN "CRUSH"                         
      DO 135 I=1,IT,LCREC                                                     
      J = I+LC1                                                               
      IF(J.GT.IT) GO TO 140                                                   
      WRITE(21) (CRUSH(L),L=I,J)                                              
 135  NOUT = NOUT+LCREC                                                       
      IT = 0                                                                  
      GO TO 150                                                               
C *** SHIFT THE INCOMPLETE RECORD IN "SQUASH" TO THE START OF THE BUFFER      
 140  J = I-1                                                                 
      IT = IT-J                                                               
      DO 145 I=1,IT                                                           
 145  CRUSH(I) = CRUSH(J+I)                                                   
 150  CONTINUE                                                                
      CLOSE (11)                                                              
      IF(IT.LT.1) GO TO 165                                                   
C *** WRITE THE FINAL COMPRESSED DATA STREAM;                                 
C     PAD ANY TERMINAL BYTES OF THE RECORD WITH BINARY ZEROES                 
      DO 160 I=1,IT,LCREC                                                     
      J = I+LC1                                                               
      IF(J.GT.IT)  THEN                                                       
          DO 155 L=IT+1,J                                                     
 155      CRUSH(L) = Z00                                                      
      ENDIF                                                                   
      WRITE(21) (CRUSH(L),L=I,J)                                              
 160  NOUT = NOUT+MIN(LCREC,IT-I+1)                                           
 165  CLOSE (21)                                                              
      LRB = LCBLK/LCREC                                                       
      IT = L2+NREF*2                                                          
      PCC = 1.0-FLOAT(IT)/FLOAT(L2*2)                                         
      LHR = (KH*80+LC1)/LCREC                                                 
      LHB = (LHR+LRB-1)/LRB                                                   
      K = (IT+LC1)/LCREC                                                      
      J = (K+LRB-1)/LRB                                                       
      WRITE(06,166) J,K,NREF,IT,PCC                                           
 166  FORMAT(//7X,I5,' DISK BLOCKS =',I5,' FITS RECORDS.'//                   
     &  4X,I8,' REFERENCE PIXEL(S) PROCESSED.'//                              
     &  8X,'BYTE STREAM =',I9.8//                                             
     &  8X,'IMAGE COMPRESSION =',2P,F8.4,'%')                                 
      CNAME(NH:NH) = 'H'                                                      
      INQUIRE (FILE='/'//CNAME,EXIST=FOUND)                                   
      IF(FOUND) GO TO 92                                                      
      KSTAT(1) = KSTAT(1)+1                                                   
      KSTAT(2) = KSTAT(2)+J                                                   
      KSTAT(3) = KSTAT(3)+K                                                   
C *** PREPARE THE COMPRESSED IMAGE HEADER FILE                                
      WRITE(HEADER(40)(11:30),'(12X,I8.8)') NOUT                              
      CALL FILEINF (IERR,'DEVICE',DEV,'VOLSER',DISK,TRK,1,'SECOND',1,         
     +  'RECFM','FB','LRECL',LCREC,'BLKSIZE',LCBLK)                           
      OPEN (UNIT=20,FILE='/'//CNAME,STATUS='NEW',                             
     +  FORM='UNFORMATTED',IOSTAT=IERR,ERR=92)                                
C *** WRITE THE COMPRESSED IMAGE HEADER IN "FITS" RECORDS;                    
C     PAD ANY TERMINAL 80-BYTE RECORDS WITH BLANKS                            
      DO 180 K=1,KH,36                                                        
      L = K+35                                                                
      IF(L.GT.KH)  THEN                                                       
          DO 170 I=KH+1,L                                                     
 170      HEADER(I) = VOID                                                    
      ENDIF                                                                   
      WRITE(20) (HEADER(I),I=K,L)                                             
      WRITE(06,'(/6X,1H/,I1/)') L/36                                          
      DO 175 I=K,L                                                            
      IF(HEADER(I).NE.VOID) WRITE(06,36) I,HEADER(I)                          
 175  CONTINUE                                                                
 180  CONTINUE                                                                
      CLOSE (20)                                                              
      GO TO 10                                                                
C *** THE FOLLOWING ARE TRAPS FOR ERRORS OCCURRING WHILE PROCESSING OR        
C     ATTEMPTING TO ACCESS "LSPN" OR "COMPRESS" DATA FILES                    
 190  WRITE(06,191) HNAME                                                     
      CLOSE (11)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      GO TO 10                                                                
 191  FORMAT(' *** UNABLE TO ACCESS INPUT IMAGE FILE: ',A /                   
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
 192  WRITE(06,193) CNAME                                                     
      CLOSE (11)                                                              
      CLOSE (21)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      KSTAT(5) = KSTAT(5)+1                                                   
      GO TO 10                                                                
 193  FORMAT(' *** UNABLE TO OPEN OUTPUT IMAGE FILE: ',A /                    
     &  6X,'PROCESSING OF THIS IMAGE ABORTED.')                               
 195  WRITE(06,196) K,L2,CNAME(1:NH)                                          
      CLOSE (11)                                                              
      CLOSE (21)                                                              
      KSTAT(4) = KSTAT(4)+1                                                   
      GO TO 10                                                                
 196  FORMAT(' *** DATA ERROR: INPUT STREAM ENDED AT SAMPLE ',I8,             
     2  ' OF ',I8,' EXPECTED.'/                                               
     &  6X,'PROCESSING OF ',A,' ABORTED; INCOMPLETE.')                        
 900  CONTINUE                                                                
C *** DISPLAY FINAL STATISTICS AND CLOSE                                      
      WRITE(06,905) (KSTAT(I),I=1,3)                                          
      IF(KSTAT(4).GT.0) WRITE(06,906) KSTAT(4)                                
      IF(KSTAT(5).GT.0) WRITE(06,907) KSTAT(5)                                
 905  FORMAT(1H1/' STATISTICS:'//I5,' IMAGES COMPLETED:'//                    
     &  '   TOTAL OF ',I5,' DISK BLOCKS, ',I6,' FITS RECORDS, WRITTEN.')      
 906  FORMAT(/8X,I4,' IMAGES COULD NOT BE PROCESSED.')                        
 907  FORMAT(/8X,I4,' OUTPUT FILES COULD NOT BE ALLOCATED.')                  
      STOP                                                                    
      END                                                                     
