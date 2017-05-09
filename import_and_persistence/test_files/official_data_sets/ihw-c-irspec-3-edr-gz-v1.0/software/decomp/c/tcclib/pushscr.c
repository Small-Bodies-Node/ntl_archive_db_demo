#include <alloc.h>
#include <mem.h>
#include <dos.h>
#include "tcclib.h"

#define VIDMODE   *(unsigned char far *) 0x00449lu
#define VIDSEGREG (unsigned)((7 == VIDMODE) ? 0xb000 : 0xb800 )
#define buffer PUSHPOPSCREENARRAY[PUSHPOPSCREENPTR]

char far *PUSHPOPSCREENARRAY[32];
int       PUSHPOPSCREENPTR = -1;

int PushScreen()
{
	if ( ++PUSHPOPSCREENPTR > 15 ) {
		PUSHPOPSCREENPTR--;
		return( -1 );
	}
	if ( ( buffer = (char far *)farmalloc( 4000 ) ) == NULL ) {
		MakeBox( " Out Of Memory " );
		GComm();
		return( -2 );
	}
	movedata( VIDSEGREG, 0, FP_SEG(buffer), FP_OFF(buffer), 4000 );
	return( 0 );
}

void PopScreen()
{
	movedata( FP_SEG(buffer), FP_OFF(buffer), VIDSEGREG, 0, 4000 );
	farfree( buffer );
	PUSHPOPSCREENPTR--;
}
