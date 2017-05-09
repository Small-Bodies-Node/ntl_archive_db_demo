unsigned char far *CurrentPos( void );

extern unsigned char attrib;
void TcclibInitialize( void );


void RepChar( int times, unsigned char c )
{
	unsigned char far *scptr = CurrentPos();

	TcclibInitialize();

	while ( times-- ) {
		*scptr++ = c;
		*scptr++ = attrib;
	}
}
