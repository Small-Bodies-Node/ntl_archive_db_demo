#include <conio.h>
void TcclibInitialize( void );

void BoldText()
{
	extern unsigned char attrib;
	extern unsigned char A_BOLD;

	TcclibInitialize();

	attrib = A_BOLD;
	textattr( A_BOLD );
}
