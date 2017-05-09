      SUBROUTINE JDATE(FLAG,  JAHR,MONTH,DAY,CNAME,  DJD)                     
C  SUBROUTINE DATE COMPUTES JULIAN DATE FROM CIVIL DATE,OR VICE VERSA.        
C     JAHR IS YEAR AD OR BC(INTEGER),DJD IS DOUBLE PRECISION JULIAN DATE.     
C     JAHR AND MONTH ARE INTEGERS, DAY A SINGLE PRECISION VARIABLE.           
C     IF DJD IS TO BE COMPUTED, IT MUST BE ENTERED AS ZERO.                   
C     CIVIL DATE MAY BE EITHER JULIAN OR GREGORIAN CALENDAR,DEPENDING ON FLAG.
                                                                              
C     FLAG IS 'G' FOR GREGORIAN CIVIL DATE, 'J' FOR JULIAN CIVIL DATE.        
C      CNAME IS THE NAME OF THE MONTH,SUITABLE FOR WRITING OUT (A6).          
      DIMENSION NO(12),DEPOCH(2)                                              
      CHARACTER*6 VNAME(12), CNAME                                            
      CHARACTER*1  FLAG                                                       
      INTEGER Y, JAHRJ, JAHRG                                                 
      DOUBLE PRECISION DJD,DEPOCH,DMJD                                        
      DATA VNAME/ '  JAN ','  FEB ','  MAR ','  APR ','  MAY ','  JUNE',      
     1 '  JULY','  AUG ','  SEPT','  OCT ','  NOV ','  DEC '/                 
C     JD 2159716.5 IS 1 JAN 1201AD (GREGORIAN CALENDAR).                      
      DATA DEPOCH / 260423.5D0, 2159716.5D0/                                  
      DATA NO/31,28,31,30,31,30,31,31,30,31,30,31/                            
C                                                                             
      IF ( DJD.GT.1.D-13 ) GO TO 50                                           
      Y = JAHR                                                                
      M = MONTH                                                               
      IF ( FLAG.EQ.'G' ) GO TO 10                                             
C     THE MULLER-WIMBERLY STATEMENT FOR JULIAN CALENDAR FOLLOWS.              
      J=367*Y-7*(Y+5001+(M-9)/7)/4+275*M/9+1729777                            
C  DBLE(FLOAT(J)) MAY NOT GIVE FULL PRECISION ON SOME MACHINES.               
      DJD = DBLE(DAY+FLOAT(J) ) -.5D0                                         
C                                                                             
      CNAME = VNAME(MONTH)                                                    
      RETURN                                                                  
   10 CONTINUE                                                                
C     THE MULLER-WIMBERLY STATEMENT FOR GREGORIAN CALENDAR FOLLOWS.           
      J=367*Y-7*(Y+(M+9)/12)/4-3*((Y+(M-9)/7)/100+1)/4+275*M/9+1721029        
      DJD = DAY + DBLE(J) - .5D0                                              
C                                                                             
      CNAME = VNAME(MONTH)                                                    
      RETURN                                                                  
   50 CONTINUE                                                                
      JUMP = 0                                                                
      MODE = 2                                                                
      IF ( FLAG.EQ.'J' ) MODE = 1                                             
      DMJD=DJD-DEPOCH(MODE)                                                   
      L=DMJD                                                                  
      FL=FLOAT(L)                                                             
      REMAIN=DMJD-DBLE(FL)                                                    
      LEAP = 0                                                                
      GO TO (51,52) ,  MODE                                                   
   51 JAHR = JAHRJ(L)                                                         
      IF ( MOD(JAHR,4).EQ.0)  JUMP = 1                                        
      GO TO 60                                                                
   52 N400 = L/146097                                                         
      IF ( L.EQ.146097 )  LEAP = 1                                            
      M400 = MOD(L,146097) - LEAP                                             
      N100 = M400/36524                                                       
      L = MOD( M400,36524 )                                                   
      JAHR = JAHRG( L,N100,N400 )                                             
      IF (MOD(JAHR,4).NE.0) GO TO 60                                          
      IF ( MOD(JAHR,100).NE.0)  JUMP = 1                                      
      IF ( MOD(JAHR,400).EQ.0 ) JUMP = 1                                      
   60 CONTINUE                                                                
      L = L + LEAP                                                            
      L = MOD (L,1461)                                                        
      L = (L/1460)*365 + MOD(L,365) + 1                                       
      DO 55 MONTH = 1,12                                                      
      N = NO(MONTH)                                                           
      IF (MONTH.EQ.2) N = 28+JUMP                                             
      L = L-N                                                                 
      IF (L.LE.0) GO TO 56                                                    
   55 CONTINUE                                                                
   56 CONTINUE                                                                
      CNAME = VNAME(MONTH)                                                    
      DAY = FLOAT(L+N) + REMAIN                                               
      RETURN                                                                  
      END                                                                     
      INTEGER FUNCTION JAHRJ(IL)                                              
      INTEGER IL                                                              
C                                                                             
      JAHRJ = (IL -IL/1460)/365 - 3999                                        
C                                                                             
      RETURN                                                                  
      END                                                                     
      INTEGER FUNCTION JAHRG(IL,I100,I400)                                    
      INTEGER IL,I100,I400                                                    
C                                                                             
      JAHRG = (IL-IL/1460)/365+100*I100+400*I400+1201                         
C                                                                             
      RETURN                                                                  
      END                                                                     
