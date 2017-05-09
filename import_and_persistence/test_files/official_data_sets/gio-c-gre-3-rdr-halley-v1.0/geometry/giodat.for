C-----------------------------------------------------------------------      
      SUBROUTINE GIODAT(TUTC,PVG,PVH,PVE,PVS,IERR)                            
C-------------------------------------------------                            
C S.J ISSUE 04 - 14.01.85                                                     
C------------------------                                                     
C                                                                             
************        SUBROUTINE IDENTIFICATION               ************      
*                                                                      *      
*   GIODAT IS AN INTERFACE ROUTINE TO THE EXPERIMENTER SUPPORT DATA    *      
*   FILE ON LOGICAL UNIT 7 WHICH CONTAINS POSITION DATA FOR THE        *      
*   GIOTTO PROBE, HALLEYS COMET, THE EARTH AND THE SUN GIVEN IN THE    *      
*   BARYCENTRIC REFERENCE FRAME OF 1950 ( UNITS = KM )                 *      
*   THE APPROXIMATE VELOCITIES FOR THE FOUR BODIES WILL BE DERIVED     *      
*   FROM THE POSITION DATA AND GIVEN IN KM/S.                          *      
*                                                                      *      
************************************************************************      
*                                                                      *      
************        VARIABLE IDENTIFICATIONS               *************      
*                                                                      *      
*****            INPUT VARIABLES ( ARGUMENT LIST )                   ***      
*                                                                      *      
*   TUTC      -  UNIVERSAL COORDINATED TIME                            *      
*                                                                      *      
*****            OUTPUT VARIABLES ( ARGUMENT LIST )                  ***      
*                                                                      *      
*   PVG(1-3)  -  POSITION OF THE GIOTTO PROBE AT TIME TUTC             *      
*   PVG(4-6)  -  VELOCITY OF THE GIOTTO PROBE AT TIME TUTC             *      
*   PVH(1-3)                                                           *      
*   PVH(4-6)  -  DITTO FOR HALLEYS COMET                               *      
*   PVE(1-3)                                                           *      
*   PVE(4-6)  -  DITTO FOR THE EARTH                                   *      
*   PVS(1-3)                                                           *      
*   PVS(4-6)  -  DITTO FOR THE SUN                                     *      
*   IERR      -  ERROR CODE                                            *      
*                =0 : NO ERROR                                         *      
*                =1 : TUTC TOO EARLY                                   *      
*                =2 : TUTC TOO LATE                                    *      
*                                                                      *      
*****            LOCAL VARIABLES                                     ***      
*                                                                      *      
*   ET        -  MODIFIED JULIAN DAY ( 1950.0 )                        *      
*   PGI(3,3)  -  TO STORE POSITION DATA FOR THREE SAMPLES FOR THE      *      
*                GIOTTO PROBE. USED IN THE QUADRATIC INTERPOLATION     *      
*   PHI(3,3)  -  DITTO FOR HALLEYS COMET                               *      
*   PEI(3,3)  -  DITTO FOR THE EARTH                                   *      
*   PSI(3,3)  -  DITTO FOR THE SUN                                     *      
*   T(3)      -  TO STORE REFERENCE TIME T0 FOR THE INTERPOLATION.     *      
*   SKIP(3)   -  USED FOR SKIPPING RECORDS ON THE DATA FILE            *      
*                                                                      *      
*****            CONSTANT IDENTIFICATION                             ***      
*                                                                      *      
*   HSEC      -  (STEP SIZE)*86400                                     *      
*   LUN       -  LOGICAL UNIT NUMBER OF FILE/TAPE CONTAINING           *      
*                THE POSITION DATA.                                    *      
*   STPUTC    -  STEP IN UTC AT TIME SUTC                              *      
*                                                                      *      
*****            TEMPORARY VARIABLES                                 ***      
*                                                                      *      
*   (1)                                                                *      
*   TMP(1)    -  0.5*(P-1)*(P-2)                                       *      
*   TMP(2)    -  P*(P-2)                                               *      
*   TMP(3)    -  0.5*P*(P-1)                                           *      
*   (2)                                                                *      
*   TMP(1)    -  (P-1.5)/HSEC                                          *      
*   TMP(2)    -  (2*P-2)/HSEC                                          *      
*   TMP(3)    -  (P-0.5)/HSEC                                          *      
*   WHERE ET(P) IS THE EPOCH FOR WHICH THE POSITIONS ARE REQUIRED.     *      
*   ( RANGE OF P :  0:2 )                                              *      
*                                                                      *      
************************************************************************      
*                                                                      *      
************  TYPE DECLARATION AND STORAGE ALLOCATION       ************      
*                                                                      *      
      IMPLICIT REAL*8(A-H,O-Z)                                                
      LOGICAL FIRST                                                           
      CHARACTER*1 SKIP(3)                                                     
      DIMENSION PVG(6),PVH(6),PVE(6),PVS(6),PGI(3,3),                         
     &          PHI(3,3),PEI(3,3),PSI(3,3),T(3),TMP(3)                        
      DATA FIRST/.TRUE./                                                      
      DATA LUN/7/                                                             
*                                                                      *      
************************************************************************      
*                                                                      *      
************  INITIALISE AND VERIFY INPUT DATA              ***BLOCK0000      
*                                                                      *      
      IERR=0                                                                  
C    IF FIRST CALL READ CONTROL RECORD AND SET CONSTANTS                      
      IF(FIRST)THEN                                                           
      READ(LUN,10) IYGEN,MOGEN,DGEN,NS,NR,ET1,ET2,SUTC,ETUTC1,ETUTC2          
   10 FORMAT(2I5,D16.10,2I5,/,5D16.10)                                        
C    DETERMINE STEP IN UTC AT TIME SUTC                                       
      STPUTC=ETUTC2-ETUTC1                                                    
C    DETERMINE STEP SIZE                                                      
      H=(ET2-ET1)/(DFLOAT(NR)-1.D0)                                           
      HSEC=H*86400.D0                                                         
      FIRST=.FALSE.                                                           
      ENDIF                                                                   
C    DETERMINE ET ( MODIFIED JULIAN DAY )                                     
      ET=TUTC+ETUTC1                                                          
      IF(ET.GE.SUTC)ET=ET+STPUTC                                              
C    VERIFY EPOCH ON FILE                                                     
      IF(ET.LT.ET1-1.D-4)GOTO 910                                             
      IF(ET.GT.ET2+1.D-4)GOTO 920                                             
      DO 20 I=1,6                                                             
      PVG(I)=0.D0                                                             
      PVH(I)=0.D0                                                             
      PVE(I)=0.D0                                                             
      PVS(I)=0.D0                                                             
   20 CONTINUE                                                                
*                                                                      *      
************************************************************************      
*                                                                      *      
*******    DETERMINE THE POSITION VECTORS FOR EPOCH ET       **BLOCK0100      
*                                                                      *      
C    FIND RECORD NUMBER                                                       
      IREC=DINT((ET-ET1)/H)+1                                                 
  110 CONTINUE                                                                
C    POSITION THE T0 SAMPLE TO ENSURE T1 AND T2 AVAILABLE                     
      IF(NR-IREC.LT.2)THEN                                                    
      IREC=IREC-1                                                             
      GOTO 110                                                                
      ENDIF                                                                   
      IF(FIRST)THEN                                                           
      IF(IREC.EQ.1)GOTO 135                                                   
      DO 130 I=1,IREC-1                                                       
      READ(LUN,120)SKIP                                                       
  120 FORMAT(A1)                                                              
  130 CONTINUE                                                                
      GOTO 135                                                                
      ENDIF                                                                   
      IDIF=IREC-IRECOL-1                                                      
      IF(IDIF.EQ.0)GOTO 135                                                   
      ISKP=ABS(IDIF)                                                          
      IF(IDIF.LT.0)THEN                                                       
      DO 131 I=1,ISKP*3                                                       
      BACKSPACE LUN                                                           
  131 CONTINUE                                                                
      ELSE                                                                    
      DO 132 I=1,ISKP                                                         
      READ(LUN,120)SKIP                                                       
  132 CONTINUE                                                                
      ENDIF                                                                   
  135 CONTINUE                                                                
      IRECOL=IREC+2                                                           
      DO 150 I=1,3                                                            
      READ(LUN,140)T(I),(PGI(I,J),J=1,3),(PHI(I,J),J=1,3),                    
     &                  (PEI(I,J),J=1,3),(PSI(I,J),J=1,3)                     
  140 FORMAT(5D16.10)                                                         
  150 CONTINUE                                                                
*                                                                      *      
*                                                                      *      
************************************************************************      
*                                                                      *      
*******  DETERMINE POSITIONS USING QUADRATIC INTERPOLATION   **BLOCK0200      
*                                                                      *      
      P=(ET-T(1))/H                                                           
      TMP(1)=0.5*(P-1.D0)*(P-2.D0)                                            
      TMP(2)=P*(2.D0-P)                                                       
      TMP(3)=0.5D0*P*(P-1.D0)                                                 
      DO 220 I=1,3                                                            
      DO 210 J=1,3                                                            
      PVG(I)=PVG(I)+PGI(J,I)*TMP(J)                                           
      PVH(I)=PVH(I)+PHI(J,I)*TMP(J)                                           
      PVE(I)=PVE(I)+PEI(J,I)*TMP(J)                                           
      PVS(I)=PVS(I)+PSI(J,I)*TMP(J)                                           
  210 CONTINUE                                                                
  220 CONTINUE                                                                
*                                                                      *      
************************************************************************      
*                                                                      *      
******* DETERMINE VELOCITIES FROM DERIVATIVES OF POSITIONS   **BLOCK0300      
*                                                                      *      
      TMP(1)=(P-1.5D0)/HSEC                                                   
      TMP(2)=(2.D0-2.D0*P)/HSEC                                               
      TMP(3)=(P-0.5D0)/HSEC                                                   
      DO 320 I=1,3                                                            
      I1=I+3                                                                  
      DO 310 J=1,3                                                            
      PVG(I1)=PVG(I1)+PGI(J,I)*TMP(J)                                         
      PVH(I1)=PVH(I1)+PHI(J,I)*TMP(J)                                         
      PVE(I1)=PVE(I1)+PEI(J,I)*TMP(J)                                         
      PVS(I1)=PVS(I1)+PSI(J,I)*TMP(J)                                         
  310 CONTINUE                                                                
  320 CONTINUE                                                                
*                                                                      *      
************************************************************************      
*                                                                      *      
************             ERROR MESSAGE BLOCK                ***BLOCK0900      
*                                                                      *      
      GOTO 1000                                                               
  910 CONTINUE                                                                
      IERR=1                                                                  
      WRITE(6,911)                                                            
  911 FORMAT(/' GIOPOS :  ET TOO EARLY ')                                     
      GOTO 1000                                                               
  920 CONTINUE                                                                
      IERR=2                                                                  
      WRITE(6,921)                                                            
  921 FORMAT(/' GIOPOS :  ET TOO LATE ')                                      
 1000 CONTINUE                                                                
      RETURN                                                                  
      END                                                                     
C----------------------------------------------------------------------       
