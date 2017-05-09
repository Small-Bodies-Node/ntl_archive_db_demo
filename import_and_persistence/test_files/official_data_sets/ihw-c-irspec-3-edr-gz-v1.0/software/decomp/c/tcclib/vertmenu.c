#include "tcclib.h"
#include <string.h>
#include <stdlib.h>
#include <conio.h>
#include <ctype.h>
#include <stdio.h>

void VertMenu( MenuRec MP[], int numchoices, int x, int y, int xx, int yy )
{
	register int i, ptr=0, longest=0;
	int begx, begy;
	int ch;
	char *screen;

	for (i=0; i<numchoices; ++i) {
		if ( strlen( MP[i].Item ) > longest )
			longest = strlen( MP[i].Item );
	}

	begx = x;
	begy = y;
	while ( begx+1+longest	> 80 ) begx--;
	while ( begy+1+numchoices > 25 ) begy--;
	if ( begx < 0 ) begx = 0;
	if ( begy < 0 ) begy = 0;
	screen = (char *) calloc( 4000, 1 );
	GetScreen( screen );
	ExplodeBox( begx, begy, begx+1+longest, begy+1+numchoices );
	for (i=0; i<numchoices; ++i)
		AtSay( begx+1, begy+1+i, MP[i].Item );
	for (;;) {
		ChangeBlock( begx, begy, begx+1+longest, numchoices+begy+1, 0x07 );
		ChangeBlock( begx+1, begy+1+ptr, begx+longest, begy+1+ptr, 0x70 );
		AtSay( xx, yy, MP[ptr].Desc );
		switch( ch = GComm() ) {
			case CR:
				if ( -1 == MP[ptr].func() ) {
					PutScreen( screen );
					free( screen );
					return;
				}
				break;
			case ESC:
				PutScreen( screen );
				free( screen );
				return;
			case DOWN:
				if ( ++ptr >= numchoices )
					ptr = 0;
				break;
			case UP:
				if ( --ptr < 0 )
					ptr = numchoices - 1;
				break;
			default:
				i = ptr + 1;
				while ( i != ptr ) {
					if ( i >= numchoices ) i = 0;
					if ( toupper(MP[i].Item[0]) == toupper(ch) ||
						 MP[i].FuncKey == ch ) {
						ptr = i;
						break;
					}
					i++;
				}
				break;
		}
	}
}
