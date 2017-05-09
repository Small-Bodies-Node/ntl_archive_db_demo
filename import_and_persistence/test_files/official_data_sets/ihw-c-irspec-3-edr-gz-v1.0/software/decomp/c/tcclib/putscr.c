#include <mem.h>
#include <dos.h>

#define VIDMODE   *(unsigned char far *) 0x00449lu
#define VIDSEGREG (unsigned)((7 == VIDMODE) ? 0xb000 : 0xb800 )

void PutScreen( char *buffer )
{
	movedata( FP_SEG(buffer), FP_OFF(buffer), VIDSEGREG, 0, 4000 );
}
