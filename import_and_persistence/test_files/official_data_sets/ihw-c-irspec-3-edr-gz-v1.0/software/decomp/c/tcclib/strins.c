#include <alloc.h>
#include <string.h>

char *strins ( char *string, int start, char *insstr )
{
    register int smaxlen, imaxlen;
    char *success;

    smaxlen = strlen (string);
    imaxlen = strlen (insstr);
    --start; /* make start zero relative */
    if ( start < smaxlen ) {   /* +1 for null char */
        success = malloc (smaxlen - start + 1 );
        memcpy (success, &string[start], smaxlen - start + 1);
        memcpy (&string[start], insstr, imaxlen);
        memcpy (&string[start] + imaxlen, success, smaxlen - start + 1);
        free (success);
        success = string;
    }
    else
        success = NULL;

    return (success);
}

