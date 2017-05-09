#include <string.h>

void StrRpl( char *string, int startpos, int num, char *replacestring )
{
	char s1[240];

	strcpy( s1, string + startpos + num - 1 );
	string[startpos] = 0;
	strcat( string, replacestring );
	strcat( string, s1 );
}
