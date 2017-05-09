#include <dos.h>

void GetCursor( int *Top, int *Bottom )
{
	union REGS reg;

	reg.x.ax = 0x0300;
	int86( 0x10, &reg, &reg );
	*Bottom = reg.h.ch & 15;
	*Top	= reg.h.cl & 15;
}
