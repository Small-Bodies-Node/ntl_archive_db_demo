#include <stdio.h>

unsigned char far *ScrPtr( int x, int y );
void TcclibInitialize( void );

void AtSayF( int col, int row, char *fmt, ... )
{
	register unsigned char far *scptr;
	char s[240];
	register char *cp = s;
	va_list argptr;
	extern unsigned char attrib;

	TcclibInitialize();

	va_start( argptr, format );
	vsprintf( s, fmt, argptr );
	va_end( argptr );

	scptr = ScrPtr( col, row );
	while (*cp) {
		 *scptr++ = *cp++;
		 *scptr++ = attrib;
	}
}
