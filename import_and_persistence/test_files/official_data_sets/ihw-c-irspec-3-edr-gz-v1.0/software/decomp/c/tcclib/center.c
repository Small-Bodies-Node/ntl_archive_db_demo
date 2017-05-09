void AtSay( int x, int y, char *s );

#include <string.h>

void Center( int y, char *s )
{
	AtSay( ( 80 - strlen(s) ) / 2, y, s );
}
