void AtSay( int x, int y, char *s );

#include <stdio.h>

#include <string.h>

void CenterF( int y, char *fmt, ... )
{
	char s[240];
	va_list ap;

	va_start( ap, format );
	vsprintf( s, fmt, ap );
	va_end( ap );

	AtSay( ( 80 - strlen(s) ) / 2, y, s );
}
