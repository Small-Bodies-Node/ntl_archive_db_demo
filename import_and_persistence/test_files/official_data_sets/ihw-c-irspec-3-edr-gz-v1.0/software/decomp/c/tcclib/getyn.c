#include <string.h>
#include <conio.h>
#include "tcclib.h"
#include <ctype.h>

int GetYN (char *s)
{
    char Scr[800];
    int p, x, xx, ch;

    p = ( 80 - strlen( s ) ) / 2;
    x = p - 3;
    xx = p + strlen( s ) + 2;
    if ( x  <  1 ) x  = 1;
    if ( xx > 80 ) xx = 80;
    gettext( x, 8, xx, 12, Scr );
    ExplodeBox( x, 8, xx, 12 );
    AtSay( p, 10, s );
    ChangeBlock( x, 8, xx, 12, A_REVERSE );
    ch = GComm();
    puttext( x, 8, xx, 12, Scr );
    if ( toupper( ch ) == 'Y' )
        return( 1 );
    return( 0 );
}
