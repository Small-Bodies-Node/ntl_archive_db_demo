#define VIDMODE   *(unsigned char far *) 0x00449lu
#define MONOSEG  0xb0000000L
#define COLOR 0xb8000000L
#define VIDSEG (unsigned char far *)((7 == VIDMODE) ? MONOSEG : COLOR )

unsigned char far *ScrPtr( int col, int row )
{
	return( VIDSEG + (--row * 160) + (--col << 1) );
}
