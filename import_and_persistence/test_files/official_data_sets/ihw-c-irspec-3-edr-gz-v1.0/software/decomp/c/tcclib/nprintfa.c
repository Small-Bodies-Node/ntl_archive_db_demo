void SayA( int attrib, char *s );

#include <stdio.h>

void NPrintFA( int num, int attrib, char *fmt, ... )
{
	char s[240];
	va_list ap;

	va_start( ap, format );
	vsprintf( s, fmt, ap );
	va_end( ap );
	s[num] = 0;
	SayA( attrib, s );
}
