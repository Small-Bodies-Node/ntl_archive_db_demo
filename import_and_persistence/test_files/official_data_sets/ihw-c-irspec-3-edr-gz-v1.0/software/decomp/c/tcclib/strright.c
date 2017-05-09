#include <string.h>

void StrRight( char *dest, char *src, int num )
{
	strcpy( dest, src + strlen( src ) - num );
}
