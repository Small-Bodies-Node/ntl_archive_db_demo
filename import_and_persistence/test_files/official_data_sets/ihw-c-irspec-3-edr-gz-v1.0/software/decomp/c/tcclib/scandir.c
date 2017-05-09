#include <stdio.h>
#include <dir.h>
#include <string.h>

typedef struct {
			 char Name[9];
			 char Ext[4];
			 char Attribute;
	unsigned int  Date;
	unsigned int  Time;
	unsigned long Size;
			 int  Tag;
} FileStruc;

int ScdirDone;

FileStruc *XScanDir( char *Mask, int Type );

FileStruc *ScanDir( char *Mask, int Type )
{
	static char OldMask[80] = {"\0\0"};

	if ( 0 != stricmp( Mask, OldMask ) ) {
		strcpy( OldMask, Mask );
		ScdirDone = 1;
	}
	return( XScanDir( Mask, Type ) );
}

FileStruc *XScanDir( char *Mask, int Type )
{
	static FileStruc FS;
	register FileStruc *fp=&FS;
	register char *cp;
	static struct ffblk ffblkx;

Rescan:
	if ( ScdirDone )
		ScdirDone = findfirst( Mask, &ffblkx, Type );
	else
		ScdirDone = findnext ( &ffblkx );

	if ( ScdirDone )
		return( NULL );

	if ( ffblkx.ff_name[0] == '.' ) goto Rescan;

	memset( fp, 0, sizeof( FileStruc ) );

	if ( ( cp = strchr( ffblkx.ff_name, '.' ) ) != NULL ) {
		*cp = 0;
		cp++;
		strcpy( fp->Ext, cp );
	}
	strcpy( fp->Name, ffblkx.ff_name );
	fp->Size	  = ffblkx.ff_fsize;
	fp->Date	  = ffblkx.ff_fdate;
	fp->Time	  = ffblkx.ff_ftime;
	fp->Attribute = ffblkx.ff_attrib;
	fp->Tag	   = 32;

	return( fp );
}
