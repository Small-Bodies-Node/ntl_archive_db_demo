#include <dos.h>

int CheckPrn()
{
	union REGS regs;

	regs.h.ah = 2;
	regs.x.dx = 0;
	int86( 0x17, &regs, &regs );
	if ( regs.h.ah == 144 )
	   return( 1 );
	else
	   return( 0 );
}
