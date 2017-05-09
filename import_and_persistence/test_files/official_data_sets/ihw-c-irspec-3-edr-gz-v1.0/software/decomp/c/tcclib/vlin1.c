unsigned char far *ScrPtr( int x, int y );

void VLin1( int x, int y, int xx, int yy )
{
	register unsigned char far *scptr = ScrPtr( x, y );
	register int i;

	if ( y == yy ) {
		switch( *scptr ) {
			case 205 : *scptr = 216; break;
			case 196 : *scptr = 197; break;
			default  : *scptr = 179; break;
		}
		return;
	}

	switch( *scptr ) {
		case 205 : *scptr = 209; break;
		case 196 : *scptr = 194; break;
		default  : *scptr = 179; break;
	}

	for (i=y+1; i<yy; ++i) {
		scptr+=160;
		switch( *scptr ) {
			case 205 : *scptr = 216; break;
			case 196 : *scptr = 197; break;
			default  : *scptr = 179; break;
		}
	}

	scptr+=160;
	switch( *scptr ) {
		case 205 : *scptr = 207; break;
		case 196 : *scptr = 193; break;
		default  : *scptr = 179; break;
	}
}
