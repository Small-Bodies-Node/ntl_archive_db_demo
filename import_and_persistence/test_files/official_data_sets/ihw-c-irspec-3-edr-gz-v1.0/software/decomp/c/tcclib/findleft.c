typedef struct {
	int   x;
	int   y;
	int   Len;
	int   Type;
	char *Address;
	int   EditFlag;
	int   NumDecimals;
} FieldStruc;

FindLeft( FieldStruc *Field, int pos, int NUMFIELDS )
{
	register int i;
	register int choice = -1;
	register FieldStruc *FS1, *FS3, *FS2=Field + pos;

	FS1 = Field;
	for (i=0; i<NUMFIELDS; ++i) {
		if ( i == pos )				 goto Increment;
		if ( FS1->EditFlag == 0 )	   goto Increment;
		if ( FS1->y != FS2->y )		 goto Increment;
		if ( FS1->x > FS2->x )		  goto Increment;
		if ( choice == -1 ) {
			choice = i;
			FS3 = FS1;
		}
		else
			if ( FS1->x > FS3->x ) {
				choice = i;
				FS3 = FS1;
			}
Increment:
		FS1++;
	}
	if ( choice == -1 )
		return( pos );
	else
		return( choice );
}
