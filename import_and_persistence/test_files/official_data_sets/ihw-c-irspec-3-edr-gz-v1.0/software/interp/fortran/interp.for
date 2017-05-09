       Subroutine Interp(tobs,v)                                              
c************************************************************                 
c..                                                                           
c Interp Reads and Interpolates Comet Ephemeris File at Time                  
c        Tobs and Uses a Seven Point LAGRANGIAN INTERPOLATION                 
c************************************************************                 
c..INPUT                                                                      
c  Tobs  =  D.P. JED Observation Time When COMET EPHEMERIS                    
c           FILE is to be Interpolated.                                       
c                                                                             
c..OUTPUT                                                                     
c  V     = (V(I),I=1,11)=D.P. Interpolated Comet Values                       
c***********************************************************                  
      Implicit Double Precision(A-H,O-Z)                                      
      Character*1 Isign                                                       
      Dimension   Val(11,7), T(7), V(11)                                      
      Integer     key, Ih, Id, Iread                                          
c                                                                             
      Data Iread/1/, Oldtob/0.0d0/, rad/0.0174532925199D0/                    
c     _________Set Output Vector To Zero___________                           
      Do 1 mz=1,11                                                            
      V(mz)= 0.0d0                                                            
    1 Continue                                                                
c     ___________________________                                             
      if(Oldtob.gt.Tobs) Iread= 1                                             
      if(Iread.ne.1) go to 10                                                 
      Rewind 10                                                               
c     ___________________________                                             
c ____Store 7 Records of COMET Ephemeris into val____                         
c                                                                             
       DO 5 I=1,7                                                             
        Read(10,'(16x,f10.1,(i3,f7.3),(1x,a,i2,f6.2),2(1x,f7.4,f9.4),         
     *            f6.1,2(1x,f5.1),2f6.1)',end=80)                             
     *  T(I),Ih,Ramn,Isign,Id,Decmn,(val(M,I),M=3,11)                         
c   ___________________Convert Ra & Dec to Radians____________________        
        key= +1                                                               
        call argch2(key,Val(1,I),Val(2,I),Ih,Ramn,Isign,Id,Decmn)             
c   ------------------------------------------------------------------        
    5  CONTINUE                                                               
c                                                                             
      Iread=2                                                                 
c                                                                             
   10 continue                                                                
c  ________________________                                                   
      IF(TOBS.EQ.T(4)) then                                                   
       do 20 m= 1,11                                                          
        v(m)  = val(m,4)                                                      
   20  continue                                                               
       go to 70                                                               
      ENDIF                                                                   
c  ________________________________________________                           
      IF((TOBS-T(4))*(TOBS-T(3)).LT.0.0D0 .or.                                
     *   (TOBS-T(5))*(TOBS-T(4)).LT.0.0D0) go to 30                           
c  ________________________________________________                           
c                                                                             
c TOBS IS NOT BETWEEN REC 3 AND 4, OR 4 AND 5.. SO MOVE 6 RECORDS UP IN STACK 
       Do 28 i=1,6                                                            
        T(i)=T(i+1)                                                           
         do 24 m=1,11                                                         
          Val(m,i)=Val(m,i+1)                                                 
   24    continue                                                             
   28  Continue                                                               
c                                                                             
c  _____________ NOW READ RECORD INTO 7TH AREA OF STACK_________________      
        Read(10,'(16x,f10.1,(i3,f7.3),(1x,a,i2,f6.2),2(1x,f7.4,f9.4),         
     *            f6.1,2(1x,f5.1),2f6.1)',end=80)                             
     *  T(7),Ih,Ramn,Isign,Id,Decmn,(val(M,7),M=3,11)                         
c   ___________________Convert Ra & Dec to Radians____________________        
        key= +1                                                               
        call argch2(key,Val(1,7),Val(2,7),Ih,Ramn,Isign,Id,Decmn)             
c   ------------------------------------------------------------------        
      go to 10                                                                
c  _______________________________________________                            
   30 Do 60 iio= 1,7                                                          
       x= 1                                                                   
       do 40 iin= 1,7                                                         
        if(iin.eq.iio) go to 40                                               
        x= x * (Tobs - T(iin)) / (T(iio) - T(iin))                            
   40  continue                                                               
        do 50 m= 1,11                                                         
          if(m.eq.1) then                                                     
           if(((Val(1,7)/rad)-(Val(1,iio)/rad)).gt.345.0d0) then              
            v(m) = v(m) + x * (val(1,iio)+(360.0d0*rad))                      
            go to 50                                                          
           endif                                                              
          endif                                                               
         v(m) = v(m) + x * val(m,iio)                                         
   50   continue                                                              
   60 Continue                                                                
c  _______________________________________________                            
   70 Oldtob = Tobs                                                           
      return                                                                  
   80 Oldtob = 9999999.0d0                                                    
      write(*,'(///,46h Observation Time is not in this Ephemeris Set)')      
      return                                                                  
      End                                                                     
