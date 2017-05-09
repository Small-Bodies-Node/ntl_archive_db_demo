void TcclibInitialize( void );
unsigned char far *ScrPtr( int x, int y );

void BlockErase( int x, int y, int xx, int yy )
{
	register int j;
	register unsigned char far *scptr;
	register int i;
	extern unsigned char A_NORMAL;

	TcclibInitialize();

	for (i=y; i<=yy; ++i) {
		scptr = ScrPtr( x, i );
		for (j=x; j<=xx; ++j) {
			*scptr++ = ' ';
			*scptr++ = A_NORMAL;
		}
	}
}
