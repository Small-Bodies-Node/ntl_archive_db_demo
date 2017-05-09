void AtSayF( int x, int y, char *ftm, ... );

#include <alloc.h>

void PutFree( int x, int y, char *format )
{
	AtSayF( x, y, format, coreleft(), 0 );
}
