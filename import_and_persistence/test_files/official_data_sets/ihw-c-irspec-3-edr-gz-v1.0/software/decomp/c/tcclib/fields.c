#include <stdio.h>
#include <conio.h>
#include <mem.h>
#include "tcclib.h"
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int  GComm( void );
void AtSay( int x, int y, char *s );
void Beep( void );
void BlockErase( int x, int y, int xx, int yy );
void NormalText( void );
void ReverseText( void );
char *RevJul( unsigned d );
unsigned Julian( char *s );
void	 TcclibInitialize( void );

extern unsigned char A_REVERSE;
extern unsigned char A_NORMAL;

int GetField( FieldStruc *Field )
{
	register int ch;
	register int j=0;
	register char *Ptr;
	register int i;
	char Temp[90];
	int RetVal;
	int y;
	int x;
	int Length;
	char Format[20];
	int CharTyped = 0;

	TcclibInitialize();

	y = Field->y;
	x = Field->x;
	Length = Field->Len;

	BlockErase ( x, y, x+Length-1, y );
	ChangeBlock( x, y, x+Length-1, y, A_REVERSE );
	ReverseText();

	if ( Field->Type ){
		Ptr = Temp;
		memset( Temp, 0, sizeof(Temp) );
		 /*
		 *	Non-zero Type indicates a non-textstring value.
		 */
		switch( Field->Type ){
			case F_INT:
				sprintf( Format, "%%%dd", Length );
				sprintf(Temp, Format, *(int *) Field->Address);
				break;
			case F_INT0:
				sprintf( Format, "%%0%dd", Length );
				sprintf(Temp, Format, *(int *) Field->Address);
				break;
			case F_CHAR:
				sprintf(Temp, "%c", *(Field->Address));
				break;
			case F_LNG:
				sprintf( Format, "%%%dld", Length );
				sprintf(Temp, Format, *(long *) Field->Address);
				break;
			case F_LNG0:
				sprintf( Format, "%%0%dld", Length );
				sprintf(Temp, Format, *(long *) Field->Address);
				break;
			case F_DATE:
				sprintf(Temp, "%s", RevJul( *(unsigned *) Field->Address ) );
				break;
			case F_DBL:
				sprintf( Format, "%%%d.%df", Length, Field->NumDecimals );
				sprintf(Temp, Format, *(double *) Field->Address);
				break;
			case F_FLT:
				sprintf( Format, "%%%d.%df", Length, Field->NumDecimals );
				sprintf(Temp, Format, *(float *) Field->Address);
				break;
			case F_BLN:
				sprintf(Temp, "%c", (*(Field->Address)) ? 'Y' : 'N' );
				break;
		}
	}
	else
		 Ptr = Field->Address;

	AtSay( x, y, Ptr );

	j = 0;

	gotoxy( x, y );

	for( ;; ){

		  switch( ch = GComm() ){

			   case SHFT_TAB:
			   case CTL_RIGHT:
					RetVal = LEFT;
					goto end_GetField;

			   case TAB:
			   case CTL_LEFT:
					RetVal = RIGHT;
					goto end_GetField;

			   case CR:
			   case LF:
			   case DOWN:
			   case UP:
			   case PGDN:
			   case PGUP:
			   case HOME:
			   case END:
			   case F1:
			   case F2:
			   case F3:
			   case F4:
			   case F5:
			   case F6:
			   case F7:
			   case F8:
			   case F9:
			   case F10:
			   case ESC:
					RetVal = ch;
					goto end_GetField;

			   case RIGHT :
					if ( !CharTyped ) {
						RetVal = RIGHT;
						goto end_GetField;
					}
					if ( j < Length ) {
					   j++;
					   if ( Ptr[j] == 0 )
						   Ptr[j] = 32;
					   gotoxy( wherex()+1, wherey() );
					}
					break;

			   case LEFT :
					if ( !CharTyped ) {
						RetVal = LEFT;
						goto end_GetField;
					}
					if ( j > 0 ) {
					   j--;
					   gotoxy( wherex()-1, wherey() );
					}
					break;

			   case INS:
					CharTyped++;
					for (i=Length; i>j; --i)
						Ptr[i] = Ptr[i-1];
					Ptr[j] = 32;
					Ptr[Length] = 0;
					AtSay( x, y, Ptr );
					gotoxy( x+j, y );
					break;

			   case BS:
					if( j == 0 ) {
						 Beep();
						 break;
					}
					j--;

			   case DEL:
					CharTyped++;
					for (i=j; i<Length; ++i) {
						Ptr[i] = Ptr[i+1];
					}
					AtSay( x, y, Ptr );
					gotoxy( x+j, y );
					break;

			   default:
					if ( !CharTyped && ch != 32 && Field->Type != F_PTR && Field->Type != F_DATE) {
						memset( Ptr, 0, Length );
						BlockErase ( x, y, x+Length-1, y );
						ChangeBlock( x, y, x+Length-1, y, A_REVERSE );
					}
					CharTyped++;
					if ( ch > 31 && ch < 128 ) {
					   if ( j < Length ) {
						  Ptr[j++] = ch;
						  putch( ch );
					   }
					}
					break;
		  }
	}

end_GetField:
	switch ( Field->Type ){
		case F_INT0:
		case F_INT:
			*(int *) Field->Address = atoi(Temp);
			sprintf( Temp, Format, *(int *) Field->Address );
			break;
		case F_CHAR:
			*(char *) Field->Address = Temp[0];
			break;
		case F_LNG0:
		case F_LNG:
			*(long *) Field->Address = atol(Temp);
			sprintf( Temp, Format, *(long *) Field->Address );
			break;
		case F_DBL:
			*(double *) Field->Address = (double) atof(Temp);
			sprintf(Temp, Format, *(double *) Field->Address);
			break;
		case F_FLT:
			*(float *) Field->Address = (float) atof(Temp);
			sprintf(Temp, Format, *(float *) Field->Address);
			break;
		case F_DATE:
			*(unsigned *) Field->Address = Julian( Temp );
			sprintf(Temp, "%s", RevJul( *(int *) Field->Address ) );
			break;
		case F_BLN:
			*(Field->Address) = ( toupper(Temp[0]) == 'Y' ) ? 1 : 0;
			break;
	};
	Ptr[ Length ] = '\0';
	NormalText();
	BlockErase( x, y, x+Length-1, y );
	AtSay( x, y, Ptr );
	ChangeBlock( x, y, x+Length-1, y, A_NORMAL );
	return( RetVal );
}


void PutField( FieldStruc *Field )
{
	register char *Ptr;
	char Temp[90];
	char Format[20];

	TcclibInitialize();

	if ( Field->Type ){
		Ptr = Temp;
		memset( Temp, 0, sizeof(Temp) );
		 /*
		 *	Non-zero Type indicates a non-textstring value.
		 */
		switch( Field->Type ){
			case F_INT:
				sprintf( Format, "%%%dd", Field->Len );
				sprintf(Temp, Format, *(int *) Field->Address);
				break;
			case F_INT0:
				sprintf( Format, "%%0%dd", Field->Len );
				sprintf(Temp, Format, *(int *) Field->Address);
				break;
			case F_CHAR:
				sprintf(Temp, "%c", *(Field->Address));
				break;
			case F_LNG:
				sprintf( Format, "%%%dld", Field->Len );
				sprintf(Temp, Format, *(long *) Field->Address);
				break;
			case F_LNG0:
				sprintf( Format, "%%0%dld", Field->Len );
				sprintf(Temp, Format, *(long *) Field->Address);
				break;
			case F_DBL:
				sprintf( Format, "%%%d.%df", Field->Len, Field->NumDecimals );
				sprintf(Temp, Format, *(double *) Field->Address);
				break;
			case F_FLT:
				sprintf( Format, "%%%d.%df", Field->Len, Field->NumDecimals );
				sprintf(Temp, Format, *(float *) Field->Address);
				break;
			case F_DATE:
				sprintf(Temp, "%s", RevJul( *(int *) Field->Address ) );
				break;
			case F_BLN:
				sprintf(Temp, "%c", (*(Field->Address)) ? 'Y' : 'N' );
				break;
		}
	}
	else
		 Ptr = Field->Address;

	BlockErase( Field->x, Field->y, Field->x + Field->Len - 1, Field->y );
	AtSay( Field->x, Field->y, Ptr );
}
