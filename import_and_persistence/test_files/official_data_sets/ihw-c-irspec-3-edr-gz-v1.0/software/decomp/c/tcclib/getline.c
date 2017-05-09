#include <conio.h>
#include <string.h>
#include "tcclib.h"

int GetLine( char *ptr, int dsize, int start )
{
    register int ch;
	register int j=start;
	register int x;
	register int i;
	register int y;

	x = wherex();
	y = wherey();
	AtSay( x, y, ptr );
	while (j <= dsize) {
		gotoxy( x+j, y );
		switch( ch = GComm() ){
            case ESC:
                ptr[start] = '\0';
                return( -1 );
            case CR:
            case LF:
				return(strlen(ptr));
            case BS:
				if ( j )
					j--;
			case DEL:
				ch = strlen(ptr);
				for (i=j; i<ch; ++i)
					ptr[i] = ptr[i+1];
				gotoxy( x, y );
				Say( ptr );
				break;
			case INS:
				for (i=strlen(ptr)+1; i>j; --i)
					ptr[i] = ptr[i-1];
				ptr[j] = ' ';
				gotoxy( x, y );
				Say( ptr );
				break;
			case HOME:
				j = 0;
				break;
			case END:
				j = strlen(ptr);
				break;
			case LEFT:
				if (j) {
					j--;
				}
				break;
			case RIGHT:
				if (j<dsize) {
					if (ptr[j] == '\0')
						ptr[j] = ' ';
					j++;
				}
				break;
            default:
				if (j < dsize) {
                    if ( ch >= 32 && ch <= 127 ) {
                        ptr[j++] = ch;
                        putch(ch);
                    }
                }
                break;
        }
    }
    return( 0 );
}
