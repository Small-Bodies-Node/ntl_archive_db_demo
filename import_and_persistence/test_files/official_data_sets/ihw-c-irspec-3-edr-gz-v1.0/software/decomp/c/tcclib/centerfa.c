void AtSayA( int x, int y, unsigned char attrib, char *s );

#include <stdio.h>

#include <string.h>

void CenterFA( int y, unsigned char attrib, char *fmt, ... )
{
	char s[240];
	va_list ap;

	va_start( ap, format );
	vsprintf( s, fmt, ap );
	va_end( ap );

	AtSayA( ( 80 - strlen(s) ) / 2, y, attrib, s );
}
