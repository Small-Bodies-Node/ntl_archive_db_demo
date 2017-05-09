#define VIDMODE   *(unsigned char far *) 0x00449lu

int GetVidMode( void )
{
	return( VIDMODE );
}
