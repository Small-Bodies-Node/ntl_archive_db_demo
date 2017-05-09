unsigned char far *ScrPtr( int x, int y );

void ChangeBlock( int x, int y, int xx, int yy, char attrib )
{
	register int j;
	register unsigned char far *scptr;
	register int i;

	for (i=y; i<=yy; ++i) {
		scptr = ScrPtr( x, i );
		scptr++;
		for (j=x; j<=xx; ++j) {
			*scptr = attrib;
			scptr += 2;
		}
	}
}
