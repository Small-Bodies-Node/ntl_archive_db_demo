#define VIDMODE   *(unsigned char far *) 0x00449lu

void SetVidMode( int mode )
{
	VIDMODE = mode;
}
