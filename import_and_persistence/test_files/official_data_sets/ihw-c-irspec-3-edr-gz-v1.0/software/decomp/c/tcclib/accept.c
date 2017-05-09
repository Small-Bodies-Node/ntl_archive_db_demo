int  GetLine( char *s, int len, int start );
void Say( char *s );

#include <conio.h>

void Accept( int x, int y, char *Prompt, char *Buffer, int Length )
{
	gotoxy( x, y );
	Say( Prompt );
	GetLine( Buffer, Length, 0 );
}
