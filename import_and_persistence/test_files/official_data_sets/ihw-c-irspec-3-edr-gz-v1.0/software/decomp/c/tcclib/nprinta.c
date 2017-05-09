void SayA( int attrib, char *s );

void NPrintA( int num, int attrib, char *cp )
{
	char c;

	c = cp[num];
	cp[num] = 0;
	SayA( attrib, cp );
	cp[num] = c;
}
