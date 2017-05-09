unsigned char far *CurrentPos( void );

int ScrAttr()
{
	unsigned char far *scptr = CurrentPos();

	return( *(++scptr) );
}
