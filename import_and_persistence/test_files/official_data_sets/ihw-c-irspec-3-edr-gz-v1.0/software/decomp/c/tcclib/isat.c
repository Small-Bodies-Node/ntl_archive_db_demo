#define MACHTYPE  *(unsigned char far *) 0xFFFF000ELU

int IsAT()
{
	if ( MACHTYPE == 0xfc )
		return( 1 );
	return( 0 );
}
