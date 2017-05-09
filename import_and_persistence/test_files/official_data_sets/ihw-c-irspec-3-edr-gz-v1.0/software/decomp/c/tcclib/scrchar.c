unsigned char far *CurrentPos( void );

int ScrChar()
{
	unsigned char far *scptr = CurrentPos();

	return( *scptr );
}
