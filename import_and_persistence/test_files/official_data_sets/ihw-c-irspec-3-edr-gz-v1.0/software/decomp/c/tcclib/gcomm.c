#include <bios.h>
#include <stdio.h>
#include "tcclib.h"

#define MAXKEYS 64

typedef struct {
	int  Key;
	void (*Func)( void );
} GCommKeyType;

GCommKeyType GCommKey[MAXKEYS];

void GCommKeyNoOp( void );
void GCommKeyNoOp() {}

void (*GCommBackgroundFunc)( void ) = NULL;

void GCommBackground( void (*funct)( void ) )
{
	GCommBackgroundFunc = funct;
}

void GCommLink ( int key, void (*funct)( void ) )
{
	register int i;

	for (i=0; i<MAXKEYS; ++i) {
		if ( key == GCommKey[i].Key ) {
			GCommKey[i].Func = funct;
			return;
		}
	}
	for (i=0; i<MAXKEYS; ++i) {
		if ( GCommKey[i].Key == 0 ) {
			GCommKey[i].Key = key;
			GCommKey[i].Func = funct;
			return;
		}
	}
}

void GCommUnlink (int key)
{
	register int i;

	for (i=0; i<MAXKEYS; ++i) {
		if ( key == GCommKey[i].Key ) {
			GCommKey[i].Key = 0;
			GCommKey[i].Func = GCommKeyNoOp;
			return;
		}
	}
}

int GCommCheck( int Key )
{
	register int i;

	for (i=0; i<MAXKEYS; ++i)
		if ( Key == GCommKey[i].Key )
			return(1);
	return( 0 );
}

int GComm()
{
	register int i, key, rtn;

GetAnotherKey:

	while ( bioskey(1) == 0 )
		if ( GCommBackgroundFunc != NULL )
			GCommBackgroundFunc();

    key = bioskey(0);

	if ( key & 0x00ff )
		rtn = key & 0x00ff;
    else
		rtn = ( ( key & 0xff00 ) >> 8 ) | 256;

	for (i=0; i<MAXKEYS; ++i)
		if ( rtn == GCommKey[i].Key ) {
			GCommKey[i].Func();
			goto GetAnotherKey;
		}

	return( rtn );
}
