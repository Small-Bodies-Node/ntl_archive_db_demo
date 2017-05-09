int  GetLine( char *s, int len, int start );

#include <stdlib.h>

int GetInt( void )
{
	char s[20];

	GetLine( s, 18, 0 );
	return( atoi( s ) );
}
