#include <dos.h>

long MaxRAM( void )
{
	union REGS reg;

	int86( 0x12, &reg, &reg );
	return( 1024L * reg.x.ax );
}
