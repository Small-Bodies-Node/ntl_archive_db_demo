unsigned char far *ScrPtr( int x, int y );

void AtSayA( int col, int row, unsigned char attrib, register char *cp )
{
	register unsigned char far *scptr;

	scptr = ScrPtr( col, row );
	while (*cp) {
		 *scptr++ = *cp++;
		 *scptr++ = attrib;
	}
}
