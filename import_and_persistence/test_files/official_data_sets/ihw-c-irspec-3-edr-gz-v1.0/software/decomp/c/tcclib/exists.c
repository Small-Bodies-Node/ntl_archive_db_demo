#include <io.h>

int Exists( char *filename )
{
	return( access( filename, 0 ) == 0 );
}
