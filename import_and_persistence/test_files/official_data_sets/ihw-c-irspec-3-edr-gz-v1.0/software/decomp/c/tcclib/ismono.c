#define VIDMODE   *(unsigned char far *) 0x00449lu

int IsMONO()
{
	if ( VIDMODE == 7 )
		return( 1 );
	return( 0 );
}
