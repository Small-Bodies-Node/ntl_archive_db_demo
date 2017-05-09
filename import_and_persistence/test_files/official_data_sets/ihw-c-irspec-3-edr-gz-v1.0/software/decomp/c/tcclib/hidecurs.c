#include <dos.h>

void HideCursor( void )
{
	union REGS regs;

	regs.h.ah = 2;
	regs.h.bh = 0;
	regs.x.dx = 0x1950;
	int86( 0x10, &regs, &regs );
}
