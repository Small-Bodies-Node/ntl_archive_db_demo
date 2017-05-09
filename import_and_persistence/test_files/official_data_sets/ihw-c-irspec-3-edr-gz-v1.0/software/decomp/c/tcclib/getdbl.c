int  GetLine( char *s, int len, int start );

#include <stdlib.h>

double GetDouble( void )
{
	char s[20];

	GetLine( s, 18, 0 );
	return( atof( s ) );
}
