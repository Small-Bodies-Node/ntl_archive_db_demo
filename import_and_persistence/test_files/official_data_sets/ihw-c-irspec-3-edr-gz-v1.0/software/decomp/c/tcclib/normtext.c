#include <conio.h>

extern unsigned char attrib;
extern unsigned char A_NORMAL;
void TcclibInitialize( void );

void NormalText()
{
	TcclibInitialize();

	attrib = A_NORMAL;
	textattr( A_NORMAL );
}
