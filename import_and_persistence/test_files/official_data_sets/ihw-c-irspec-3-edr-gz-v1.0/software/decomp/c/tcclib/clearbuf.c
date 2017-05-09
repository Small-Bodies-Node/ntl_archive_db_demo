#include <bios.h>

void ClearBuf()
{
	while ( bioskey( 1 ) )
		bioskey( 0 );
}
