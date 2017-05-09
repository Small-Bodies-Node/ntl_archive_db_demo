#include <stdio.h>
#include <string.h>

int GComm( void );

char getchf ( char *list, char defchar )
{
    char ch;

	ch = GComm();
    if ( (strchr (list, ch)) == NULL ) {
        ch = defchar;
        putc (7, stdout);
    }

    return (ch);
}

