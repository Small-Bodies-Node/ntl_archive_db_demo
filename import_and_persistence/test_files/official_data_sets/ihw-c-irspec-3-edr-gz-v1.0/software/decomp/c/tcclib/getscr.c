#include <mem.h>
#include <dos.h>

#define VIDMODE   *(unsigned char far *) 0x00449lu
#define VIDSEGREG (unsigned)((7 == VIDMODE) ? 0xb000 : 0xb800 )

void GetScreen( char *buffer )
{
	movedata( VIDSEGREG, 0, FP_SEG(buffer), FP_OFF(buffer), 4000 );
}
