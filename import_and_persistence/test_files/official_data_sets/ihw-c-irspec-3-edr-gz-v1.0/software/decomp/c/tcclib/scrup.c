#include <dos.h>

extern unsigned char A_NORMAL;
void TcclibInitialize( void );

void ScrollUp( int x, int y, int xx, int yy, int n )
{
	union REGS regs;

	TcclibInitialize();

	regs.x.cx = ( --y << 8 ) + --x;
	regs.x.dx = ( --yy << 8 ) + --xx;
	regs.x.ax = n % 256;
	regs.h.ah = 0x06;
	regs.h.bh = A_NORMAL;
	int86( 0x10, &regs, &regs );
}
