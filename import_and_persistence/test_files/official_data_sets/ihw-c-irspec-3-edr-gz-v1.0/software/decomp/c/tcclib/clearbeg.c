char *ClearBeg( register char *cp )
{
	while ( *cp++ < 33 );
	return( --cp );
}
