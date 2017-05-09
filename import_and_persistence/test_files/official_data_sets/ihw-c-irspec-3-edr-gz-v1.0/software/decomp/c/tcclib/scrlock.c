void ScrollLock( int flag )
{
	unsigned char far *CapsKey = (unsigned char far *) 0x00417lu;

	if ( flag )
		*CapsKey = ( *CapsKey | 0x10 );
	else
		*CapsKey = ( *CapsKey & 0xef );
}
