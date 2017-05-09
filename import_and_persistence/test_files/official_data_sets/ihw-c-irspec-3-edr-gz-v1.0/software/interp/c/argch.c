                                                                              
void d2r(double *ra,double *dec,int ih,double am,                             
           char isgn,int id,double amin)                                      
                                                                              
/*    CONVERT INPUT HOURS AND DEGREES TO RADIANS */                           
                                                                              
{                                                                             
double rad= 0.0174532925199;                                                  
                                                                              
*ra=15.0 * (ih + am / 60.) * rad;                                             
*dec=((float)id + amin / 60.) * rad;                                          
if (isgn == '-') *dec = -1 * *dec;                                            
return;                                                                       
}                                                                             
                                                                              
void r2d(double *ra,double *dec,int *ih,double *am,                           
           char *isgn,int *id,double *amin)                                   
{                                                                             
char iminus = '-';                                                            
char iplus = '+';                                                             
float rad= 0.0174532925199;                                                   
float deg,hours;                                                              
int   ira;                                                                    
                                                                              
/*    CONVERT RADIANS TO DEGREES AND HOURS. */                                
                                                                              
/* compute ra */                                                              
hours = *ra / rad/ 15.0;                                                      
/* no floating point modulus function in C! */                                
while (hours > 24.) hours = hours -24.;                                       
ira = (int)hours;                                                             
*ih = ira;                                                                    
*am = (hours - (float)ira) * 60.;                                             
*am = *am+1.e-6;                                                              
if(*am >= 60.) {                                                              
      *am = *am-60.;                                                          
      *ih = *ih+1;                                                            
}                                                                             
if (*ih >= 24)                                                                
    *ih= *ih-24;                                                              
                                                                              
/* compute dec */                                                             
                                                                              
if (*dec < 0) {                                                               
   *isgn = iminus;                                                            
   *dec = - *dec;                                                             
}                                                                             
else                                                                          
   *isgn = iplus;                                                             
deg = *dec/rad;                                                               
*id=(int)deg;                                                                 
*amin=(deg-(float)*id) * 60.;                                                 
return;                                                                       
}                                                                             
