#include <conio.h>
#include <stdio.h>
#include <Gcomm.h>
#include <alloc.h>
#include <stdlib.h>
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

typedef struct {
	char Name[13];
} FileNameStruc;

#define MAXFILES 500

FileStruc *ScanDir( char *Mask, int Type );
char *FileName( FileStruc *f );

int  GComm( void );
void HideCursor( void );
void BlockErase( int x, int y, int xx, int yy );
void DrawBox( int x, int y, int xx, int yy );
void DrawBox1( int x, int y, int xx, int yy );
void HLin( int x, int y, int xx, int yy );
void ChangeBlock( int x, int y, int xx, int yy, unsigned char attrib );
void AtSay( int x, int y, register char *s );
void AtSayF( int x, int y, char *fmt, ... );
void NPrintFA( int len, unsigned char attrib, char *fmt, ... );
int  GetLine( char *s, int len, int start );
void TcclibInitialize( void );

extern int ScdirDone;
extern unsigned char A_NORMAL;
extern unsigned char A_REVERSE;

int CompareFileNames( FileNameStruc *A, FileNameStruc *B )
{
	return( strcmp( A->Name, B->Name ) );
}

char *GetFile( int x, int y, int xx, int yy,
			 char *Mask, int FileAttrib, int ExtOn )
{
	register FileNameStruc *File;
	register int i;
	int j, cols, rows, Width, files, top, oldptr, oldtop;
	static char RetName[15];
	FileStruc *fp;
	int ptr;
	char *Scr1, *Scr2;
	int ch;

	TcclibInitialize();

	cols = xx - x + 1;
	rows = yy - y + 1;
	if ( cols < 14 || rows < 4 ) return( NULL );
	if ( ( Scr1 = (char *) calloc( rows*2, cols ) ) == NULL )
		return( NULL );
	if ( ( Scr2 = (char *) calloc( 6, 14 ) ) == NULL )
		return( NULL );
	if ( ( File = (FileNameStruc *) calloc( MAXFILES, 13 ) ) == NULL )
		return( NULL );
	gettext( x, y, xx, yy, Scr1 );
	BlockErase( x, y, xx, yy );
	DrawBox( x, y, xx, yy );
	gotoxy( x+1, y );
	NPrintFA( cols-4, A_REVERSE, " %s ", Mask );
	x++;
	y++;
	xx--;
	yy--;
	files = 0;
	ScdirDone = 1;
	while ( ( fp = ScanDir( Mask, FileAttrib ) ) != NULL && files < MAXFILES-1 ) {
		if ( ExtOn )
			strcpy( File[files++].Name, FileName( fp ) );
		else
			strcpy( File[files++].Name, fp->Name );
	}
	if ( ExtOn )
		Width = 14;
	else
		Width = 10;
	rows -= 2;
	cols = (xx - x + 3) / Width;

	qsort(  File, files, sizeof( FileNameStruc ), CompareFileNames );

	top = 0;

RedrawFiles:

	BlockErase( x, y, xx, yy );
	if ( top > 0 )
		AtSay( xx-9, yy+1, "PgUp/" );
	else
		HLin( xx-9, yy+1, xx-5, yy+1 );
	if ( cols * rows + top < files ) {
		AtSay( xx-4, yy+1, "PgDn" );
	}
	else
		HLin( xx-5, yy+1, xx-1, yy+1 );

	for ( i=0;  i<cols;  ++i )
		for ( j=0;  j<rows;  ++j )
			if ( files > ( i * rows + j + top ) )
				AtSayF( x + Width * i, y + j,
						"%s", File[i * rows + j + top].Name );

	ptr = 0;

	for (;;) {

		ChangeBlock( x, y, xx, yy, A_NORMAL );
		ChangeBlock( x + ( ptr / rows * Width ),
					 y + ( ptr % rows ),
					 x + ( ptr / rows * Width ) + Width - 3,
					 y + ( ptr % rows ),
					 A_REVERSE );

		oldptr = ptr;
		oldtop = top;
		HideCursor();
		switch( ch = GComm() ) {
			case CR:
				strcpy( RetName, File[ptr + top].Name );
				puttext( x-1, y-1, xx+1, yy+1, Scr1 );
				free( Scr1 );
				free( Scr2 );
				free( File );
				return( RetName );
			case ESC:
				puttext( x-1, y-1, xx+1, yy+1, Scr1 );
				free( Scr1 );
				free( Scr2 );
				free( File );
				return( NULL );
			case DOWN:
				if ( ptr < rows * cols - 1 )
					ptr++;
				break;
			case UP:
				if ( ptr )
					ptr--;
				break;
			case RIGHT:
				if ( ptr < rows * cols - rows )
					ptr += rows;
				break;
			case LEFT:
				if ( ptr >= rows )
					ptr -= rows;
				break;
			case PGDN:
				if ( top + rows * cols < files ) {
					top += rows * cols;
					goto RedrawFiles;
				}
				break;
			case PGUP:
				if ( top ) {
					top -= rows * cols;
					if ( top < 0 ) top = 0;
					goto RedrawFiles;
				}
				break;
			default:
				if ( ch > ' ' && ch < 128 ) {
					gettext( x-1, y, x+12, y+2, Scr2 );
					BlockErase( x-1, y, x+12, y+2 );
					DrawBox1( x-1, y, x+12, y+2 );
					AtSay( x+2, y, "New File" );
					gotoxy( x, y+1 );
					putch( ch );
					memset( RetName, 0, sizeof (RetName) );
					RetName[0] = ch;
					if ( -1 == GetLine( RetName, 12, 1 ) ) {
						puttext( x-1, y, x+12, y+2, Scr2 );
						break;
					}
					puttext( x-1, y-1, xx+1, yy+1, Scr1 );
					free( Scr1 );
					free( Scr2 );
					free( File );
					return( RetName );
				}
				break;
		}
		if ( top < 0 ) top = 0;
		if ( !strlen( File[top+ptr].Name ) ) {
			ptr = oldptr;
			top = oldtop;
		}
	}
}
