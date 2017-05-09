unsigned char far *ScrPtr( int x, int y );

void VLin( int x, int y, int xx, int yy )
{
	register unsigned char far *scptr = ScrPtr( x, y );
	register int i;

	if ( y == yy ) {
		switch( *scptr ) {
			case 205 : *scptr = 206; break;
			case 196 : *scptr = 215; break;
			default  : *scptr = 186; break;
		}
		return;
	}

	switch( *scptr ) {
		case 205 : *scptr = 203; break;
		case 196 : *scptr = 210; break;
		default  : *scptr = 186; break;
	}

	for (i=y+1; i<yy; ++i) {
		scptr+=160;
		switch( *scptr ) {
			case 205 : *scptr = 206; break;
			case 196 : *scptr = 215; break;
			default  : *scptr = 186; break;
		}
	}

	scptr+=160;
	switch( *scptr ) {
		case 205 : *scptr = 202; break;
		case 196 : *scptr = 208; break;
		default  : *scptr = 186; break;
	}

}
