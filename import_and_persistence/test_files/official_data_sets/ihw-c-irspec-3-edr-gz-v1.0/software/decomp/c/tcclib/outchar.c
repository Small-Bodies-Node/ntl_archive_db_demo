unsigned char far *CurrentPos( void );

void TcclibInitialize( void );
extern unsigned char attrib;

void OutChar( unsigned char c )
{
	register unsigned char far *scptr = CurrentPos();

	TcclibInitialize();

	*scptr++ = c;
	*scptr   = attrib;
}
