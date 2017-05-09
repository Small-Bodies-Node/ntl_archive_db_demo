#include <string.h>

char *ClearEnd( register char *cp )
{
	register char *end = cp + strlen( cp ) - 1;
	while ( *end < 33 )
		*end-- = 0;
	return( cp );
}
