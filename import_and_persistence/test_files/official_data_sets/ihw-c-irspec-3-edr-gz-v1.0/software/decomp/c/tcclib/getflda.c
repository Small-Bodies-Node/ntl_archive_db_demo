typedef struct {
	int   x;
	int   y;
	int   Len;
	int   Type;
	char *Address;
	int   EditFlag;
	int   NumDecimals;
} FieldStruc;

int  GetField( FieldStruc *f );

int GetFieldA( int x, int y, int len, int type, char *address )
{
	FieldStruc Field;

	Field.x		= x;
	Field.y		= y;
	Field.Len	  = len;
	Field.Type	 = type;
	Field.Address  = address;
	Field.EditFlag = 1;
	return( GetField( &Field ) );
}
