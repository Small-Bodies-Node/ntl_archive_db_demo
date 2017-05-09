#include <alloc.h>
#include <string.h>

char *strdel ( char *string, int start, int num )
{
    register int maxlen;
    char *success;

    maxlen = strlen (string);
    if ( start < maxlen ) {
        --start;  /* make start zero relative */
        if ( start + num >= maxlen )
            string[start] = '\0';
        else
            memcpy (&string[start], &string[start] + num,
                (maxlen + 1) - (start + num)); /* must include the null char */
        success = string;
    }
    else
        success = NULL;

    return (success);
}

