#include <dos.h>

void CursorOn()
{
	union REGS reg;

	reg.x.ax = 0x0300;
	int86( 0x10, &reg, &reg );

	reg.x.ax = 0x0100;
	reg.h.ch = (reg.h.ch & 0x0f);
	int86( 0x10, &reg, &reg );
}
