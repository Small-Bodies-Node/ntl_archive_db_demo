#include "tcclib.h"
#include <conio.h>
#include <dos.h>

int WindowLister( int x,
                  int y,
                  int xx,
                  int yy,
                  int CharToExitOn,
                  int *NumItems,
                  int NumToStartWith,
				  int (*CharHandler)(int ch, int Index),
                  void (*ScreenClearer)( void ),
                  void (*DisplayLineFunction)(int Index) )
{
    register int i;
    register int CurrLine = 0;
    int PrevLine;
    int NumLines = yy - y;
    int Top = NumToStartWith;
    int OldTop;
    int RefreshScreen = 1;
    int ch;
    union REGS regs, regs2;
    int Index;

    regs.x.cx = ( (y-1) << 8 ) + x-1;
    regs.x.dx = ( (yy-1) << 8 ) + xx-1;
    regs.x.ax = 0;
    regs.h.bh = A_NORMAL;
    regs.h.ah = 0x06;
    regs.x.ax = 1;

    for (;;) {

        if ( RefreshScreen ) {
            ScreenClearer();
            ChangeBlock( x, y, xx, yy, A_NORMAL );
            RefreshScreen = 0;
            PrevLine = 500;
            OldTop = -500;
            for (i=0; i<=NumLines; ++i) {
                gotoxy( x, i+y );
                if ( i+Top <= *NumItems )
                    DisplayLineFunction( i+Top );
            }
        }

        Index = CurrLine + Top;

        if ( PrevLine != CurrLine || OldTop != Top ) {
			SetAttrib( A_REVERSE );
            gotoxy( x, y + CurrLine );
            DisplayLineFunction( Index );
			SetAttrib( A_NORMAL );
        }
        HideCursor();

        PrevLine = CurrLine;
        OldTop = Top;

        ch = GComm();
        if ( ch == CharToExitOn ) return( Index );

        switch( ch ) {

			case ESC:
				return( -1 );

			case DOWN:
                CurrLine++;
                if (CurrLine > NumLines) {
                    CurrLine = NumLines;
                    if ( Top < *NumItems - NumLines ) {
                        Top++;
                           ChangeBlock( x, y, xx, yy, A_NORMAL );
                        regs.h.ah = 0x06;
                        int86( 0x10, &regs, &regs2 );
                        gotoxy( x, yy );
                    }
                }
                break;

            case UP:
                CurrLine--;
                if (CurrLine < 0) {
                    CurrLine=0;
                    if ( Top > 0 ) {
                        Top--;
                           ChangeBlock( x, yy, xx, yy, A_NORMAL );
                        regs.h.ah = 7;
                        int86( 0x10, &regs, &regs2 );
                        gotoxy( x, y );
                    }
                }
                break;

            case END:
                Top = *NumItems - NumLines;
                if ( Top < 0 ) {
                    Top = 0;
                }
                CurrLine = NumLines;
                if ( CurrLine > *NumItems ) {
                    CurrLine = *NumItems;
                }
                RefreshScreen++;
                break;

            case PGDN:
                Top += NumLines;
                if (Top > *NumItems - NumLines) {
                    Top = *NumItems - NumLines;
                    if ( Top < 0 )
                        Top = 0;
                    CurrLine = NumLines;
                    if ( CurrLine > *NumItems ) {
                        CurrLine = *NumItems;
                    }
                }
                RefreshScreen++;
                break;

            case PGUP:
                Top -= NumLines;
                if (Top < 0) {
                    Top = 0;
                    CurrLine = 0;
                }
                RefreshScreen++;
                break;

            case HOME:
                Top = CurrLine = 0;
                RefreshScreen++;
                break;

            default:
                gotoxy( x, CurrLine + y );
                ReverseText();
                DisplayLineFunction( Index );
                NormalText();
				/*HideCursor();*/
                RefreshScreen++;
				CurrLine = CharHandler( ch, Index );
				RefreshScreen = 1;
				if ( CurrLine - Top <= NumLines && CurrLine - Top >= 0 )
					CurrLine -= Top;
				else {
					Top = CurrLine - 1;
					CurrLine = 1;
					if ( Top < 0 ) {
						Top = CurrLine = 0;
					}
                }
                break;

        }
        if ( Top < 0 ) {
            Top = 0;
            CurrLine = 0;
        }
        if ( Top + CurrLine > *NumItems ) {
            CurrLine = *NumItems - Top;
        }
        if ( PrevLine != CurrLine || Top != OldTop ) {
            ChangeBlock( x, y, xx, yy, A_NORMAL );
        }
        if ( RefreshScreen > 1 ) {
            Top = CurrLine = 0;
        }
    }
}
