unsigned char far *ScrPtr( int x, int y );
void TcclibInitialize( void );

void AtSay( int col, int row, register char *cp )
{
	register unsigned char far *scptr;
	extern unsigned char attrib;

	TcclibInitialize();
	scptr = ScrPtr( col, row );
	while (*cp) {
		 *scptr++ = *cp++;
		 *scptr++ = attrib;
	}
}
