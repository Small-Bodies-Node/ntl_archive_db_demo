#include "tcclib.h"
#include <ctype.h>
#include <conio.h>
#include <string.h>

void HorzMenu( MenuRec *MenuPtr, int NumChoices, int x, int y, int xx, int yy )
{
	register int pos=0, i;
	register MenuRec *MP = MenuPtr;
	int start[20], len[20], tlen=0, longest=0;
	int ch;

	gotoxy( x, y );
	for (i=0; i<NumChoices; ++i) {
		start[i] = wherex();
		len[i] = strlen( MP->Item );
		SayF( "%s  ", MP->Item );
		tlen += len[i] + 2;
		if ( strlen( MP->Desc ) > longest ) longest = strlen( MP->Desc );
		MP++;
	}

	for ( ;; ) {
		ChangeBlock( x, y, tlen, y, A_NORMAL );
		BlockErase( xx, yy, xx+longest-1, yy );
		ChangeBlock( start[pos], y, start[pos] + len[pos] - 1, y, A_REVERSE );
		AtSay( xx, yy, MenuPtr[pos].Desc );
		HideCursor();
		switch( ch = GComm() ) {
			case RIGHT:
				pos++;
				if ( pos >= NumChoices )
					pos = 0;
				break;
			case LEFT:
				pos--;
				if ( pos < 0 )
					pos = NumChoices - 1;
				break;
			case CR:
				ChangeBlock( x, y, tlen, y, A_NORMAL );
				BlockErase( xx, yy, xx+longest-1, yy );
				MenuPtr[pos].func();
				break;
			case ESC:
				BlockErase( x, y, tlen, y );
				BlockErase( xx, yy, xx+longest-1, yy );
				return;
			default:
				for (i=pos+1; i<NumChoices; ++i) {
					if ( toupper(ch) == toupper( *MenuPtr[i].Item ) ) {
						pos = i;
						goto EndDefault;
					}
				}
				for (i=0; i<pos; ++i) {
					if ( toupper(ch) == toupper( *MenuPtr[i].Item ) ) {
						pos = i;
						break;
					}
				}
EndDefault:
				break;
		}
	}
}
