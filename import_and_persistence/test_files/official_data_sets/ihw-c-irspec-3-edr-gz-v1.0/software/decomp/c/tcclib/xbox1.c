unsigned char far *ScrPtr( int x, int y );

void XDrawBox1( x, y, xx, yy )
{
	register unsigned char far *scptr = ScrPtr( x, y );
	register int i;

	*scptr = 218;
	scptr++;
	scptr++;
	for (i=x+1; i<xx; ++i) {	/* top line */
		*scptr = 196;
		scptr++;
		scptr++;
	}
	*scptr = 191;			/* top left corner */
	scptr += 160;
	for (i=y+1; i<yy; ++i) {	/* right side */
		*scptr = 179;
		scptr += 160;
	}
	*scptr =  217;		   /* bottom right corner */
	scptr--;
	scptr--;
	for (i=x+1; i<xx; ++i) {
		*scptr = 196;		/* bottom line */
		scptr--;
		scptr--;
	}
	*scptr = 192;			/* bottom left corner */
	scptr -= 160;
	for (i=y+1; i<yy; ++i) {	/* left side */
		*scptr = 179;
		scptr -= 160;
	}
}
