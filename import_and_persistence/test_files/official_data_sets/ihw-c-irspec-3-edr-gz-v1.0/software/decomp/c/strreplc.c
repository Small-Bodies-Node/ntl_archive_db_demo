#include <string.h>                                                           
                                                                              
char *strreplace (char *string, int start, int num, char *repstr )            
{                                                                             
    int x = 0;                                                                
    char *success;                                                            
                                                                              
    --start;                                                                  
    /* "start" becomes zero relative */                                       
    if ( start < strlen (string) ) {                                          
        while ( string[start] != '\0' && *repstr != '\0' && x < num )         
            { string[start++] = *repstr;  ++repstr; x++; }                    
        success = string;                                                     
    }                                                                         
    else                                                                      
        success = NULL;                                                       
                                                                              
    return (success);                                                         
}                                                                             
                                                                              
