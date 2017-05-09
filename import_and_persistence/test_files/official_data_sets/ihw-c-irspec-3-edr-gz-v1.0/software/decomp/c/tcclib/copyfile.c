#include <stdio.h>
#include <fcntl.h>
#include <alloc.h>
#include <io.h>

int CopyFile( char *src, char *dest )
{
	int fd1, fd2, num;
	char *buffer;

	if ( ( buffer = (char *) calloc( 32001, 1 ) ) == NULL ) {
		return( -1 );
	}

	if ( ( fd1 = open( src, O_RDONLY | O_BINARY ) ) == EOF ) {
		free( buffer );
		return( -2 );
	}

	if ( ( fd2 = _creat( dest, 0 ) ) == EOF ) {
		free( buffer );
		return( -3 );
	}

	while ( ( num = read( fd1, buffer, 32000 ) ) > 0 ) {
		if ( num != write( fd2, buffer, num ) )
			return( -4 );
	}

	close( fd2 );
	close( fd1 );
	free( buffer );
	return( 0 );
}
