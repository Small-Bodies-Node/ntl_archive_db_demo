#include <conio.h>

#define VIDMODE   *(unsigned char far *) 0x00449lu
#define MONOSEG  0xb0000000L
#define COLOR 0xb8000000L
#define VIDSEG (unsigned char far *)((7 == VIDMODE) ? MONOSEG : COLOR )

void RestoreCurPos( unsigned char far *scptr )
{
	unsigned long p = (unsigned long) scptr - (unsigned long) VIDSEG;
	p /= 2;
	p += 81;
	gotoxy( (unsigned) p % 80, (unsigned) p / 80 );
}
