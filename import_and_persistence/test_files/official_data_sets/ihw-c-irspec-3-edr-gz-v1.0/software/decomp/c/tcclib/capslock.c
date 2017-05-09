void CapsLock( int flag )
{
	unsigned char far *CapsKey = (unsigned char far *) 0x00417lu;

	if ( flag )
		*CapsKey = ( *CapsKey | 0x40 );
	else
		*CapsKey = ( *CapsKey & 0xbf );
}
