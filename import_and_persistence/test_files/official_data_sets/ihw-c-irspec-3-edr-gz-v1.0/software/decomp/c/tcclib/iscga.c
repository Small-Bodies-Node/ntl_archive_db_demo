#define VIDMODE   *(unsigned char far *) 0x00449lu

int IsCGA()
{
	if ( VIDMODE != 7 && VIDMODE < 0x0b)
		return( 1 );
	return( 0 );
}
