#include <conio.h>

extern unsigned char attrib;
void TcclibInitialize( void );

void SetAttrib( char attribute  )
{
	TcclibInitialize();

	attrib = attribute;
	textattr( attribute );
}
