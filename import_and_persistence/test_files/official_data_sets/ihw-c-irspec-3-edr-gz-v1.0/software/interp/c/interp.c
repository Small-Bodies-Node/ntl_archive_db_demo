#include <stdio.h>                                                            
#include <stdlib.h>                                                           
                                                                              
                                                                              
void interp(double tobs,double *v,FILE *fpin)                                 
/*                                                                            
  Interp Reads and Interpolates Comet Ephemeris File at Time                  
        Tobs and Uses a Seven Point LAGRANGIAN INTERPOLATION                  
..INPUT                                                                       
  Tobs     =  D.P. JED Observation Time When COMET EPHEMERIS                  
              FILE is to be Interpolated.                                     
                                                                              
..OUTPUT                                                                      
  V    = (V(I),I=0,10)=D.P. Interpolated Comet Values  */                     
                                                                              
{                                                                             
double val[11][7], t[7], ramn, decmn, x;                                      
int    ih, id, m, iio, iin, i, k, status,i1,i2,i3,i5;                         
float  f4;                                                                    
char   isign;                                                                 
static int iread=1;                                                           
static double oldtob = 0.0;                                                   
static double rad = 0.0174532925199;                                          
#define f1 "\n %d %d %d %f %lf %d %lf %c %d %lf %lf %lf %lf %lf %lf %lf %lf\  
    %lf %lf"                                                                  
                                                                              
/*     Set Output Vector To Zero */                                           
for (i = 0; i < 11; i++) v[i] = 0.0;                                          
                                                                              
if (oldtob > tobs) iread = 1;                                                 
                                                                              
if (iread != 1) goto l10;                                                     
                                                                              
rewind(fpin);                                                                 
                                                                              
/* Store 7 Records of COMET Ephemeris into val */                             
                                                                              
for (i=0;i<7;i++){                                                            
 status= fscanf(fpin,f1,&i1,&i2,&i3,&f4,&t[i],&ih,&ramn,&isign,&id,&decmn,    
      &val[2][i],&val[3][i],&val[4][i],&val[5][i],&val[6][i],&val[7][i],      
      &val[8][i],&val[9][i],&val[10][i]);                                     
if (status == EOF) goto l80;                                                  
/*   printf(f1,i1,i2,i3,f4,t[i],ih,ramn,isign,id,decmn,val[2][i],             
       val[3][i],val[4][i],val[5][i],val[6][i],val[7][i],                     
       val[8][i],val[9][i],val[10][i]);     */                                
                                                                              
/*  Convert Ra & Dec to Radians */                                            
                                                                              
     d2r(&val[0][i],&val[1][i],ih,ramn,isign,id,decmn);                       
}                                                                             
iread=2;                                                                      
l10:                                                                          
if (tobs == t[3]) {                                                           
   for (m=0;m<11;m++) {                                                       
       v[m] = val[m][3];                                                      
   }                                                                          
   goto l70;                                                                  
}                                                                             
if ((tobs-t[3])*(tobs-t[2]) < 0.0 || (tobs-t[4])*(tobs-t[3])< 0.0) {          
   goto l30;                                                                  
}                                                                             
/* TOBS IS NOT BETWEEN REC 3 AND 4, OR 4 AND 5..                              
   SO MOVE 6 RECORDS UP IN STACK*/                                            
for (i=0;i<6;i++){                                                            
      t[i] = t[i+1];                                                          
      for (m=0;m<11;m++) {                                                    
         val[m][i]=val[m][i+1];                                               
      }                                                                       
 }                                                                            
                                                                              
/*  NOW READ RECORD INTO 7TH AREA OF STACK */                                 
 status= fscanf(fpin,f1,&i1,&i2,&i3,&f4,&t[6],&ih,&ramn,&isign,&id,&decmn,    
      &val[2][6],&val[3][6],&val[4][6],&val[5][6],&val[6][6],                 
      &val[7][6],&val[8][6],&val[9][6],&val[10][6]);                          
 if (status == EOF) goto l80;                                                 
 /*printf(f1,i1,i2,i3,f4,t[6],ih,ramn,isign,id,decmn,val[2][6],               
       val[3][6],val[4][6],val[5][6],val[6][6],val[7][6],                     
       val[8][6],val[9][6],val[10][6]);*/                                     
                                                                              
/*   Convert Ra & Dec to Radians  */                                          
                                                                              
d2r(&val[0][6],&val[1][6],ih,ramn,isign,id,decmn);                            
                                                                              
 goto l10;                                                                    
 l30:                                                                         
 for (iio=0;iio<7;iio++) {                                                    
   x= 1.0;                                                                    
   for (iin=0;iin<7;iin++) {                                                  
       if (iin !=iio) {                                                       
          x= x * ((tobs - t[iin]) / (t[iio] - t[iin]));                       
       }                                                                      
   }                                                                          
   for (m=0;m<11;m++) {                                                       
       if ((m == 0) &&(((val[0][6]/rad)-(val[0][iio]/rad))>345.0)) {          
             v[m] = v[m] + x * (val[0][iio]+(360.0*rad));                     
       } else {                                                               
         v[m] = v[m] + x * val[m][iio];                                       
       }                                                                      
   }                                                                          
}                                                                             
 l70:                                                                         
 oldtob = tobs;                                                               
 return;                                                                      
 l80:                                                                         
 oldtob = 9999999.0;                                                          
 printf("\n\n\n Observation Time is not in this Ephemeris Set");              
 return;                                                                      
}                                                                             
                                                                              
