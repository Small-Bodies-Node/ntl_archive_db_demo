void Say( char *s );

#include <stdio.h>

void NPrintF( int num, char *fmt, ... )
{
	char s[240];
	va_list ap;

	va_start( ap, format );
	vsprintf( s, fmt, ap );
	va_end( ap );
	s[num] = 0;
	Say( s );
}
