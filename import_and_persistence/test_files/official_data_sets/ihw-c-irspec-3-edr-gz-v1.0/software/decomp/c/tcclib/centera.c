void AtSayA( int x, int y, unsigned char attrib, char *s );

#include <string.h>

void CenterA( int y, unsigned char attrib, char *s )
{
	AtSayA( ( 80 - strlen(s) ) / 2, y, attrib, s );
}
