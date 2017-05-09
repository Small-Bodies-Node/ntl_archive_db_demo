#include <dos.h>

int IsEGA()
{
	union REGS reg;

	reg.x.ax = 0x1200;
	reg.x.bx = 0x0010;
	reg.x.cx = 0xffff;

	int86( 0x10, &reg, &reg );

	if ( reg.x.cx == 0xffff )
		return(0);
	else
		return(1);
}
