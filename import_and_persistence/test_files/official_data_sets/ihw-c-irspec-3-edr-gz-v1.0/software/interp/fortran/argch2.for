      SUBROUTINE ARGCH2(KEY,RA,DEC,IH,AM,ISGN,ID,AMIN)                        
      IMPLICIT DOUBLE PRECISION (A-H,O-Z)                                     
      CHARACTER*1 ISGN,IMINUS,IPLUS                                           
      DATA IPLUS,IMINUS /'+','-'/                                             
      DATA RAD/0.0174532925199D0/                                             
C****                                                                         
C**** KEY .NE. -1 ---- HOURS AND DEGREES TO RADIANS                           
C**** KEY .EQ. -1 ---- RADIANS TO HOURS AND DEGREES                           
C****                                                                         
      IF(KEY.EQ.-1) GO TO 10                                                  
      H=IH                                                                    
      D=ID                                                                    
      RA=15.D0*(H+AM/6.D1)* RAD                                               
      DEC=(D+AMIN/6.D1)*RAD                                                   
      IF(ISGN.EQ.'-') DEC=-DEC                                                
      RETURN                                                                  
   10 HOURS=RA/RAD/15.D0                                                      
      HOURS=DMOD(HOURS,24.D0)                                                 
      IH=HOURS                                                                
      AM=(HOURS-IH)*6.D1                                                      
      AM=AM+1.D-6                                                             
      IF(AM.LT.6.D1) GO TO 12                                                 
      AM=AM-6.D1                                                              
      IH=IH+1                                                                 
   12 IF(IH.GE.24) IH=IH-24                                                   
      IF(DEC) 15,20,20                                                        
   15 ISGN=IMINUS                                                             
      GO TO 25                                                                
   20 ISGN=IPLUS                                                              
   25 DEG=DABS(DEC)/RAD                                                       
      ID=DEG                                                                  
      AMIN=(DEG-ID)*6.D1                                                      
      RETURN                                                                  
      END                                                                     
