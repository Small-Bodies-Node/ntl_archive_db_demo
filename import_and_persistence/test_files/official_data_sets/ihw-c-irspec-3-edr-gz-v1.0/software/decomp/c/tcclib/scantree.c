#include <stdio.h>
#include <dos.h>
#include <mem.h>

typedef struct {
			 char Name[9];
			 char Ext[4];
			 char Attribute;
	unsigned int  Date;
	unsigned int  Time;
	unsigned long Size;
			 int  Tag;
} FileStruc;

FileStruc *XScanDir( char *Mask, int Type );
char *FileName( FileStruc *f );

extern int ScdirDone;

#define DTASIZE 43

int ScanTree( char *path, char *filespec, int (*FN)(FileStruc *f, char *p) )
{
	char mask[80], s[80];
	FileStruc *fs;
	char far *TempDta;
	char Dta[DTASIZE];

	sprintf( mask, "%s%s", path, filespec );
	ScdirDone = 1;
	while ( ( fs = XScanDir( mask, 0xff ) ) != NULL ) {
		if ( -1 == FN( fs, path ) ) return( -1 );
	}
	sprintf( mask, "%s*.*", path );
	while ( ( fs = XScanDir( mask, FA_DIREC ) ) != NULL ) {
		if ( fs->Attribute & FA_DIREC ) {
			TempDta = getdta();
			movedata( FP_SEG(TempDta), FP_OFF(TempDta), _DS, (unsigned) Dta, DTASIZE );
			sprintf( s, "%s%s\\", path, FileName( fs ) );
			if ( -1 == ScanTree( s, filespec, FN ) ) return( -1 );
			ScdirDone = 0;
			setdta( TempDta );
			movedata( _DS, (unsigned) Dta, FP_SEG(TempDta), FP_OFF(TempDta), DTASIZE );
		}
	}
	return( 1 );
}
