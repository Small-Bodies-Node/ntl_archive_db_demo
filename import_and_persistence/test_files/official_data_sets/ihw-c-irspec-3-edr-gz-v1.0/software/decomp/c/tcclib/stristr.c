#include <ctype.h>
#include <string.h>
#include <alloc.h>

char *stristr ( char *string1, char *string2 )
{
    char *cp;

    cp = string2;
    if ( *cp != '\0' ) {
        while ( *string1 != '\0' && *cp != '\0' ) {
            cp = string2;
            while ( toupper (*string1) == toupper (*cp) && *string1 != '\0' && *cp != '\0' ) {
                ++string1;  ++cp;
            }
            ++string1;
        }
        if ( *cp == '\0' ) cp = string1 - strlen (string2) - 1;
        else cp = NULL;
    }
    else cp = NULL;

    return (cp);
}

