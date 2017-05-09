unsigned char far *ScrPtr( int x, int y );

void HLin( int x, int y, int xx, int yy )
{
	register unsigned char far *scptr = ScrPtr( x, y );
	register int i;

	if ( x == xx ) {
		switch( *scptr ) {
			case 186 : *scptr = 206; break;
			case 179 : *scptr = 216; break;
			default  : *scptr = 205; break;
		}
		return;
	}

	switch( *scptr ) {
		case 186 : *scptr = 204; break;
		case 179 : *scptr = 198; break;
		default  : *scptr = 205; break;
	}
	scptr++;
	scptr++;

	for (i=x+1; i<xx; ++i) {
		switch( *scptr ) {
			case 186 : *scptr = 206; break;
			case 179 : *scptr = 216; break;
			default  : *scptr = 205; break;
		}
		scptr++;
		scptr++;
	}

	switch( *scptr ) {
		case 186 : *scptr = 185; break;
		case 179 : *scptr = 181; break;
		default  : *scptr = 205; break;
	}

}
