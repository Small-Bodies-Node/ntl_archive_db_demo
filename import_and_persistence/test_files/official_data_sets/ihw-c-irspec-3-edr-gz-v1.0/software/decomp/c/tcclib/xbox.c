unsigned char far *ScrPtr( int x, int y );

void XDrawBox( x, y, xx, yy )
{
	register unsigned char far *scptr = ScrPtr( x, y );
	register int i;

	*scptr = 201;
	scptr++;
	scptr++;
	for (i=x+1; i<xx; ++i) {	/* top line */
		*scptr = 205;
		scptr++;
		scptr++;
	}
	*scptr = 187;			/* top left corner */
	scptr += 160;
	for (i=y+1; i<yy; ++i) {	/* right side */
		*scptr = 186;
		scptr += 160;
	}
	*scptr = 188;			/* bottom right corner */
	scptr--;
	scptr--;
	for (i=x+1; i<xx; ++i) {
		*scptr = 205;		/* bottom line */
		scptr--;
		scptr--;
	}
	*scptr = 200;			/* bottom left corner */
	scptr -= 160;
	for (i=y+1; i<yy; ++i) {	/* left side */
		*scptr = 186;
		scptr -= 160;
	}
}
