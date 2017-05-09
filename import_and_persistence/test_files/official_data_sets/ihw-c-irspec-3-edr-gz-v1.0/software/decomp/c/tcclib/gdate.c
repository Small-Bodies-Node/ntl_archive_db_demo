#include "tcclib.h"
#include <conio.h>
#include <string.h>
#include <stdio.h>

extern char *CCMONTHS[12];

extern int   CCMonthDays[12];

unsigned GetDate( unsigned startdate )
{
    int i, j, r;
    int mon, day, year;
    int x, y;
    char datestr[15];

    TcclibInitialize();

    strcpy( datestr, RevJul( startdate ) );
    sscanf( datestr, "%d-%d-%d", &mon, &day, &year );
    year += 1900;
    mon--;

    DrawBox( 30,  7, 51, 18 );
    HLin( 30,  9, 51,  9 );
    HLin1( 30, 11, 51, 11 );
    AtSay( 31, 10, "Su Mo Tu We Th Fr Sa" );

REDRAW_CALENDAR:
    AtSayF( 35, 8, "%s   , %d", CCMONTHS[mon], year );
    CCMonthDays[1] = 28;
    if ( year % 4 == 0 ) {
        if ( year % 400 == 0 )
            ;
        else
            CCMonthDays[1] = 29;
    }

    sprintf( datestr, "%d-%d-%d", mon+1, 1, year );
    j = ( 1 + Julian( datestr ) ) % 7;

    r = 12;

    BlockErase( 31, 12, 50, 17 );
    for (i=1; i<CCMonthDays[mon]+1; ++i) {
        AtSayF( 31+j*3, r, "%2d", i );
        if ( i == day ) {
            x = j;
            y = r - 12;
        }
        if ( ++j > 6 ) {
            r++;
            j = 0;
        }
    }

    for ( ;; ) {
        AtSayF( 39,8,"%2d",day);
        ChangeBlock( 30, 11, 51, 18, A_NORMAL );
        ChangeBlock( 31+x*3, y+12, 32+x*3, y+12, A_REVERSE );
        switch( GComm() ) {
            case CR:
                sprintf( datestr, "%d-%d-%d", mon+1, day, year );
                return( Julian( datestr ) );
            case ESC:
                return( 0 );
            case HOME:
                day=1;
                goto REDRAW_CALENDAR;
            case END:
                day=CCMonthDays[mon];
                goto REDRAW_CALENDAR;
            case DOWN:
                y++;
                day += 7;
                break;
            case UP:
                y--;
                day -= 7;
                break;
            case LEFT:
                if ( --x < 0 ) {
                    x = 6;
                    y--;
                }
                day--;
                break;
            case RIGHT:
                if ( ++x > 6 ) {
                    x = 0;
                    y++;
                }
                day++;
                break;
            case PGUP:
                if ( --mon < 0 ) {
                    mon = 11;
                    year--;
                }
                goto REDRAW_CALENDAR;
            case PGDN:
                if ( ++mon > 11 ) {
                    mon = 0;
                    year++;
                }
                goto REDRAW_CALENDAR;
        }
        if ( day > CCMonthDays[mon] ) {
            day -= CCMonthDays[mon];
            if ( ++mon > 11 ) {
                mon = 0;
                year++;
            }
            goto REDRAW_CALENDAR;
        }
        if ( day < 1 ) {
            if ( --mon < 0 ) {
                mon = 11;
                year--;
            }
            day += CCMonthDays[mon];
            goto REDRAW_CALENDAR;
        }
    }
}
