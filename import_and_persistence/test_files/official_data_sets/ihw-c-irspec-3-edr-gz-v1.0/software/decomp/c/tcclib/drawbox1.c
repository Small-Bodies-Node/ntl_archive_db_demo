void HLin1( int x, int y, int xx, int yy );
void VLin1( int x, int y, int xx, int yy );

unsigned char far *ScrPtr( int x, int y );

void DrawBox1( int x, int y, int xx, int yy )
{
	register unsigned char far *scptr;

	if ( xx - x > 1 ) {
		HLin1( x,  y, xx,  y );
		HLin1( x, yy, xx, yy );
	}
	if ( yy - y > 1 ) {
		VLin1(  x, y,  x, yy );
		VLin1( xx, y, xx, yy );
	}
	scptr = ScrPtr(  x,  y );
	*scptr = 218;
	scptr = ScrPtr( xx,  y );
	*scptr = 191;
	scptr = ScrPtr( xx, yy );
	*scptr = 217;
	scptr = ScrPtr(  x, yy );
	*scptr = 192;
}
