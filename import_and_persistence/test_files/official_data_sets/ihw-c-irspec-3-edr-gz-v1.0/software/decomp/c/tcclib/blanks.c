int AllBlanks( register char *cp )
{
	while ( *cp )
		if ( *cp++ > 32 ) return( 0 );
	return( 1 );
}
