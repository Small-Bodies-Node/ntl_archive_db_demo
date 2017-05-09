void Say( char *s );

void NPrint( int num, char *cp )
{
	char c;

	c = cp[num];
	cp[num] = 0;
	Say( cp );
	cp[num] = c;
}
