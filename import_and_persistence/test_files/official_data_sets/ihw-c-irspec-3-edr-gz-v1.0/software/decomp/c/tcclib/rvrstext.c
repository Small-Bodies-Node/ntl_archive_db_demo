#include <conio.h>

extern unsigned char attrib;
extern unsigned char A_REVERSE;
void TcclibInitialize( void );

void ReverseText()
{
	TcclibInitialize();

	attrib = A_REVERSE;
	textattr( A_REVERSE );
}
