#include <string.h>
#include "tcclib.h"

void MakeBox( char *s )
{
    int p, x, xx;

    p = ( 80 - strlen( s ) ) / 2;
    x = p - 3;
    xx = p + strlen( s ) + 2;
    if ( x  <  1 ) x  = 1;
    if ( xx > 80 ) xx = 80;
    ExplodeBox( x, 8, xx, 12 );
    AtSay( p, 10, s );
}
