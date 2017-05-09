/*     Program ObsNterp                                                       
                                                                              
     Program ObsNterp evaluates the Comet Ephemeris given the JED Observation 
     Time. The user must input the Interpolation Time for the ephemeris to be 
     evaluated.                                                               
     The computations are performed using the Lagrange method of interpolation
                                                                              
     The number of known points is fixed at seven(7).                         
                                                                              
______________________________________________________________________________
                                                                              
 .. Designed and Implemented by:___ Ravenel N. Wimberly ___                   
                       Sterling Software                                      
                       Jet Propulsion Laboratory                              
                       Astrometry Network                                     
                       International Halley Watch                             
 .. Converted to C by:___ B. Pfarr, ST Systems Corp.                          
                       Large Scale Phenomena Network                          
                       International Halley Watch                             
____________________________________________________________________________*/
                                                                              
#include <stdio.h>                                                            
#include <stdlib.h>                                                           
                                                                              
main()                                                                        
{                                                                             
/* Declare Variables */                                                       
                                                                              
char           filenm[14], buff[20];                                          
double         tobs, values[11], ramn, decmn, day;                            
int        id, ih, ians, flag, i, n, l;                                       
FILE           *fpin;                                                         
long  int   j, y, m;                                                          
static char *vname[12] = {"  JAN ","  FEB ","  MAR ","  APR ","  MAY ",       
                "  JUNE","  JULY","  AUG ","  SEPT","  OCT ",                 
                "  NOV ","  DEC "};                                           
                                                                              
    /* Clear Screen */                                                        
printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");                       
                                                                              
l1:                                                                           
                                                                              
   /* Prompt User for Comet Ephemeris File Name */                            
printf(" What is the name of your Comet Ephemeris File ? ");                  
gets(filenm);                                                                 
                                                                              
   /* Open Comet Ephemeris file for processing */                             
if ((fpin = fopen(filenm,"r"))==NULL){                                        
   printf("Cannot open %s. Abort",filenm);                                    
   exit(-1);                                                                  
}                                                                             
l10:                                                                          
                                                                              
   /* Clear Screen */                                                         
printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");                 
                                                                              
printf(" At the Prompt Please Input Epoch for Interpolation \n");             
printf(" Please Enter Year  of Interest (1985)  --> ");                       
y = atol(gets(buff));                                                         
printf(" Please Enter Month of Interest (09)    --> ");                       
m = atol(gets(buff));                                                         
printf(" Please Enter Day   of Interest (23.47) --> ");                       
day = atof(gets(buff));                                                       
                                                                              
  /* Obtain Julian Date for Interpolation */                                  
                                                                              
    /* THE MULLER-WIMBERLY STATEMENT FOR GREGORIAN CALENDAR FOLLOWS.        */
j=367*y-7*(y+(m+9)/12)/4-3 * ((y+(m-9)/7)/100+1)/4+275 * m/9+1721029l  ;      
tobs = day + (double)j - .5  ;                                                
                                                                              
  /*  Display Date Results */                                                 
                                                                              
printf("\nThe Date is: %4ld %s %10.5f = %16.7f \n\n",y,vname[m-1],day,tobs);  
                                                                              
  /* Evaluate Comet Ephemeris at Time TOBS */                                 
                                                                              
interp(tobs,values,fpin);                                                     
                                                                              
  /* Convert Ra & Dec from radians to Hours and Degrees */                    
                                                                              
r2d(&values[0],&values[1],&ih,&ramn,&flag,&id,&decmn);                        
                                                                              
   /*  Display Results of Interpolation */                                    
printf("\n RA= %4d, %7.3f   DEC= %c %3d, %6.2f ",ih,ramn,flag,id,decmn);      
printf("\n DELTA= %8.4f , DELDOT= %8.4f , R= %7.4f ",values[2],values[3],     
     values[4]);                                                              
printf(" \n RDOT = %8.4f THETA = %5.1f , BETA = %5.1f ",values[5],values[6],  
     values[7]);                                                              
printf(" \n MOON = %6.1f PSANG = %6.1f PSAMV= %6.1f \n\n",values[8],values[9],
     values[10]);                                                             
                                                                              
/*  ... Determine if user wants to interpolate with this set of data */       
                                                                              
printf(" Interpolate More Points With This Data Set ?? ");                    
printf("  (1 = YES,   0 = NO) -----> ");                                      
ians = atoi(gets(buff));                                                      
if (ians==1) {                                                                
   goto l10;                                                                  
}                                                                             
                                                                              
/* Determine if user wants to interpolate with a new set of data */           
                                                                              
fclose(fpin);                                                                 
printf(" Interpolate With A New Set of Data ?? -");                           
printf("  (1 = YES,   0 = NO) -----> ");                                      
ians = atoi(gets(buff));                                                      
if (ians==1) {                                                                
   goto l1;                                                                   
}                                                                             
                                                                              
/*  ... If not.. Clear screen and terminate program */                        
                                                                              
printf("\n\n\n\n\n\n\n\n\n\n\n\n\n");                                         
}                                                                             
