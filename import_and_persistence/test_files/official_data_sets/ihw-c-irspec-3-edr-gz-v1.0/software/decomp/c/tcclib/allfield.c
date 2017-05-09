typedef struct {
	int   x;
	int   y;
	int   Len;
	int   Type;
	char *Address;
	int   EditFlag;
	int   NumDecimals;
} FieldStruc;

int  FindDown( FieldStruc *Field, int pos, int NUMFIELDS );
int  FindLeft( FieldStruc *Field, int pos, int NUMFIELDS );
int  FindRight( FieldStruc *Field, int pos, int NUMFIELDS );
int  FindUp( FieldStruc *Field, int pos, int NUMFIELDS );
int  GetField( FieldStruc *f );
void PutField( FieldStruc *f );

#include <GCOMM.H>

void GetAllFields( FieldStruc *Field, int NUMFIELDS, int (*ChHnd)( int ch ), void (*Update)( void ) )
{
	register int pos = 0;
	register int key;

	for (pos=0; pos<NUMFIELDS; ++pos) {
		PutField( Field + pos );
	}

	pos = 0;

	for (;;) {
		key = GetField( Field + pos );
		Update();
		switch( key ) {
			case DOWN:
				pos = FindDown ( Field, pos, NUMFIELDS );
				break;
			case UP:
				pos = FindUp   ( Field, pos, NUMFIELDS );
				break;
			case RIGHT:
				pos = FindRight( Field, pos, NUMFIELDS );
				break;
			case LEFT:
				pos = FindLeft ( Field, pos, NUMFIELDS );
				break;
			case HOME:
				pos = 0;
				break;
			case END:
				pos = NUMFIELDS-1;
				break;
			default:
				if( ChHnd( key ) < 0 ) return;
				break;
		}
		if (pos < 0) pos = 0;
		if (pos > NUMFIELDS-1) pos = NUMFIELDS-1;
	}
}
