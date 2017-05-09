unsigned char far *ScrPtr( int x, int y );

void HLin1( int x, int y, int xx, int yy )
{
	register unsigned char far *scptr = ScrPtr( x, y );
	register int i;

	if ( x == xx ) {
		switch( *scptr ) {
			case 186 : *scptr = 215; break;
			case 179 : *scptr = 197; break;
			default  : *scptr = 196; break;
		}
		return;
	}

	switch( *scptr ) {
		case 186 : *scptr = 199; break;
		case 179 : *scptr = 195; break;
		default  : *scptr = 196; break;
	}
	scptr++;
	scptr++;

	for (i=x+1; i<xx; ++i) {
		switch( *scptr ) {
			case 186 : *scptr = 215; break;
			case 179 : *scptr = 197; break;
			default  : *scptr = 196; break;
		}
		scptr++;
		scptr++;
	}

	switch( *scptr ) {
		case 186 : *scptr = 182; break;
		case 179 : *scptr = 180; break;
		default  : *scptr = 196; break;
	}

}
