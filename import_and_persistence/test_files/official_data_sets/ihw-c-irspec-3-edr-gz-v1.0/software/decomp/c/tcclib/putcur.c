#include <dos.h>

void PutCursor( int Top, int Bottom )
{
	union REGS reg;

	reg.x.ax = 0x0100;
	reg.h.ch = Bottom & 15;
	reg.h.cl = Top	& 15;
	int86( 0x10, &reg, &reg );
}
