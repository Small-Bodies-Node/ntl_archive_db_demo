#include <conio.h>

extern unsigned char attrib;
extern unsigned char A_UNDERLINE;
void TcclibInitialize( void );

void UnderlineText()
{
	TcclibInitialize();

	attrib = A_UNDERLINE;
	textattr( A_UNDERLINE );
}
