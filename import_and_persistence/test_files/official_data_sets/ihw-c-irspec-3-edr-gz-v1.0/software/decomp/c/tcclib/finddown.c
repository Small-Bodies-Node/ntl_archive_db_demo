#include <stdlib.h>

typedef struct {
	int   x;
	int   y;
	int   Len;
	int   Type;
	char *Address;
	int   EditFlag;
	int   NumDecimals;
} FieldStruc;

int FindDown( FieldStruc *Field, int pos, int NUMFIELDS )
{
	register int i;
	register int choice = -1;
	register int abs1, abs2;
	register FieldStruc *FS1, *FS3, *FS2=Field + pos;

	FS1 = Field;
	for (i=0; i<NUMFIELDS; ++i) {
		if ( i == pos )				 goto Increment;
		if ( FS1->EditFlag == 0 )	   goto Increment;
		if ( FS1->y <= FS2->y )		 goto Increment;
		abs2 = abs( FS1->x - FS2->x );
		if ( abs2 > 40 )				goto Increment;
		if ( choice == -1 ) {
			choice = i;
			FS3 = FS1;
			abs1 = abs2;
		}
		else
			if ( FS1->y < FS3->y || ( FS1->y == FS3->y && abs2 < abs1 ) ) {
				choice = i;
				FS3 = FS1;
				abs1 = abs2;
			}
Increment:
		FS1++;
	}
	if ( choice == -1 )
		return( pos );
	else
		return( choice );
}
