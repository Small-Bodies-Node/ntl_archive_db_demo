#include <dir.h>

long FileSize( char *filename )
{
	struct ffblk fb;

	if ( -1 == findfirst( filename, &fb, 0xff ) )
		return( -1 );
	return( fb.ff_fsize );
}
