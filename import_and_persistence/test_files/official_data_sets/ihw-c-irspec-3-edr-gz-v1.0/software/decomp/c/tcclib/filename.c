#include <stdio.h>

typedef struct {
			 char Name[9];
			 char Ext[4];
			 char Attribute;
	unsigned int  Date;
	unsigned int  Time;
	unsigned long Size;
			 int  Tag;
} FileStruc;

char *FileName( FileStruc *fp )
{
	static char Fn[13];

	if ( *( fp->Ext ) )
		sprintf( Fn, "%s.%s", fp->Name, fp->Ext );
	else
		sprintf( Fn, "%s", fp->Name );

	return( Fn );
}
