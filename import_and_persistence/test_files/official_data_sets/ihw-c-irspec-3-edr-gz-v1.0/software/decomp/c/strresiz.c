#include <string.h>                                                           
                                                                              
char *strresize ( char *string, int newlen )                                  
{                                                                             
    register int maxlen;                                                      
                                                                              
    maxlen = strlen (string);                                                 
    if ( maxlen < newlen ) {                                                  
        while ( maxlen < newlen )                                             
            string[maxlen++] = ' ';                                           
        string[maxlen] = '\0';                                                
    }                                                                         
    else string[newlen] = '\0';                                               
                                                                              
    return (string);                                                          
}                                                                             
                                                                              
