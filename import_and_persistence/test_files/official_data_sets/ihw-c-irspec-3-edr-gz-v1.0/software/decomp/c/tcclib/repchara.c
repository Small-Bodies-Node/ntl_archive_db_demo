unsigned char far *CurrentPos( void );

void RepCharAttr( int times, unsigned char attrib, unsigned char c )
{
	unsigned char far *scptr = CurrentPos();

	while ( times-- ) {
		*scptr++ = c;
		*scptr++ = attrib;
	}
}
