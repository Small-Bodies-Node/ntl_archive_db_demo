void HLin( int x, int y, int xx, int yy );
void VLin( int x, int y, int xx, int yy );

unsigned char far *ScrPtr( int x, int y );

void DrawBox( int x, int y, int xx, int yy )
{
	register unsigned char far *scptr;

	if ( xx - x > 1 ) {
		HLin( x,  y, xx,  y );
		HLin( x, yy, xx, yy );
	}
	if ( yy - y > 1 ) {
		VLin(  x, y,  x, yy );
		VLin( xx, y, xx, yy );
	}
	scptr = ScrPtr(  x,  y );
	*scptr = 201;
	scptr = ScrPtr( xx,  y );
	*scptr = 187;
	scptr = ScrPtr( xx, yy );
	*scptr = 188;
	scptr = ScrPtr(  x, yy );
	*scptr = 200;
}
