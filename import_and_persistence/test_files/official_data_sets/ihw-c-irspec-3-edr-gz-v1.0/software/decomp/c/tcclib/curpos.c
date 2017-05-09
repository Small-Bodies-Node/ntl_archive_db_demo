#include <dos.h>

unsigned char far *ScrPtr( int x, int y );

unsigned char far *CurrentPos( void )
{
	union REGS regin, regout;
	regin.h.ah = 3;
	regin.x.bx = 0;
	int86( 0x10, &regin, &regout );
	return( ScrPtr( regout.h.dl+1, regout.h.dh+1 ) );
}
