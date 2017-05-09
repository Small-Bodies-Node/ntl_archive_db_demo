#include <io.h>

int PutRec( int filehandle, void *buffer, int sizeofrec, long fileptr )
{
	if ( -1 == lseek( filehandle, fileptr, 0 ) ) return( -1 );
	if ( sizeofrec != write( filehandle, buffer, sizeofrec ) ) return( -1 );
	return( 0 );
}
