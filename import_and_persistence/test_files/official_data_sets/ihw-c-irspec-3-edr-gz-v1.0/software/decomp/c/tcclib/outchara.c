unsigned char far *CurrentPos( void );

void OutCharA( unsigned char attrib, unsigned char c )
{
	register unsigned char far *scptr = CurrentPos();

	*scptr++ = c;
	*scptr   = attrib;
}
