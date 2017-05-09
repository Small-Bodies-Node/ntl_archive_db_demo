      Program ObsNterp                                                        
c                                                                             
c     Program ObsNterp evaluates the Comet Ephemeris given the JED Observation
c     Time. The user must input the Interpolation Time for the ephemeris to be
c     evaluated.                                                              
c     The computations are performed using the Lagrange method of interpolatio
                                                                              
c                                                                             
c     The number of known points is fixed at seven(7).                        
c                                                                             
c_____________________________________________________________________________
                                                                              
c                                                                             
c .. Designed and Implemented by:___ Ravenel N. Wimberly ___                  
c                                    Sterling Software                        
c                                    Jet Propulsion Laboratory                
c                                    Astrometry Network                       
c                                    International Halley Watch               
c_____________________________________________________________________________
                                                                              
c                                                                             
c .. Declare Variables                                                        
c ..                                                                          
      Character        Flag*1, Month*6, Filenm*14                             
      Double Precision Tobs, Values(11), Ramn, Decmn                          
      Integer          Year, Imonth, Id, Ih, Key                              
      Real             Day                                                    
c                                                                             
c  ... Clear Screen                                                           
      Write(*,'(//////////////////////////)')                                 
c_____________________________________________________________________________
                                                                              
c ... Prompt User for Comet Ephemeris File Name                               
1     write(*,'(a)') ' What is the name of your Comet Ephemeris File ? '      
      read (*,'(a)',end=20) Filenm                                            
c ... Open Comet Ephemeris file for processing                                
      open(10,file=filenm,status='old',access='sequential',                   
     *     form='formatted')                                                  
c_____________________________________________________________________________
                                                                              
c______________________PROCESS DATA___________________________________________
                                                                              
c  ... Clear Screen                                                           
10    Write(*,'(//////////////////////////)')                                 
c                                                                             
      Write(*,*) ' At the Prompt Please Input Epoch for Interpolation'        
c                                                                             
       write(*,'(a)') ' Please Enter Year  of Interest (1985)  --> '          
       read (*,*) Year                                                        
       write(*,'(a)') ' Please Enter Month of Interest (09)    --> '          
       read (*,*) Imonth                                                      
       write(*,'(a)') ' Please Enter Day   of Interest (23.47) --> '          
       read (*,*) Day                                                         
c  ____________Set flag for Gregorian Calendar___________                     
       Flag='G'                                                               
       Tobs=0.0d0                                                             
c  ----------Call Jdate to Obtain Julian Date for Interpolation---------      
       call jdate(Flag,Year,Imonth,Day,Month,Tobs)                            
c  __________Display Date Results__________                                   
       write(*,'(/,10h Date Is: ,i4,a,f10.5,3h = ,f16.7,//)')                 
     *           Year,Month,Day,Tobs                                          
c_____________________________________________________________________________
                                                                              
c ... Evaluate Comet Ephemeris at Time TOBS                                   
c                                                                             
      call Interp(Tobs,Values)                                                
c                                                                             
c ... Convert Ra & Dec from radians to Hours and Degrees                      
      key= -1                                                                 
      call argch2(key,Values(1),Values(2),Ih,Ramn,Flag,Id,Decmn)              
c_____________________________________________________________________________
                                                                              
c ... Display Results of Interpolation                                        
      write(*,'(1x,3hRA=,i4,f7.3,5h DEC=,a,i3,f6.2,7h Delta=,f8.4,8h DEL      
     *DOT=,f8.4,3h R=,f7.4,/,6h RDOT=,f8.4,7h THETA=,f5.1,6h BETA=,f5.1,      
     *6h MOON=,f6.1,7h PSANG=,f6.1,7h PSAMV=,f6.1,//)') Ih,Ramn,Flag,Id,      
     *                                          Decmn,(values(i),i=3,11)      
c_____________________________________________________________________________
                                                                              
c  ... Determine if user wants to interpolate with this set of data           
c                                                                             
      Write(*,*) ' Interpolate More Points With This Data Set ?? -'           
      Write(*,'(a)') '    (1 = YES,   0 = NO) -----> '                        
      Read (*,*,err=10,end=10) ians                                           
      If(ians.eq.1) go to 10                                                  
c                                                                             
c  ... Determine if user wants to interpolate with a new set of data          
c                                                                             
      Close(10,status='keep')                                                 
      Write(*,*) ' Interpolate With A New Set of Data ?? -'                   
      Write(*,'(a)') '    (1 = YES,   0 = NO) -----> '                        
      Read (*,*,err=1,end=1) ians                                             
      If(ians.eq.1) go to 1                                                   
c                                                                             
c  ... If not.. Clear screen and terminate program                            
c                                                                             
20    Write(*,'(//////////////////////////)')                                 
      Close(10,status='keep')                                                 
      End                                                                     
