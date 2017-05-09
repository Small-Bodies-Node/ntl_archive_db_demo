void AtSayF( int x, int y, char *ftm, ... );

#include <dos.h>

void PutTime( int x, int y, int format )
{
/*
	formats:  1 = hh:mm:ss (military)
			  2 = hh:mm	(military)
			  3 = hh:mm:ss AM/PM
			  4 = hh:mm	AM/PM
*/
	struct time tm;
	char c;

	gettime( &tm );
	switch( format ) {
		case 1:
			AtSayF( x, y, "%2d:%02d:%02d",
				tm.ti_hour, tm.ti_min, tm.ti_sec );
			break;
		case 2:
			AtSayF( x, y, "%2d:%02d",
				tm.ti_hour, tm.ti_min );
			break;
		case 3:
			c = 'A';
			if ( tm.ti_hour > 11 ) {
				c = 'P';
				tm.ti_hour -= 12;
			}
			if ( tm.ti_hour == 0 )  tm.ti_hour = 12;
			AtSayF( x, y, "%2d:%02d %cM",
				tm.ti_hour, tm.ti_min, tm.ti_sec, c );
			break;
		case 4:
			c = 'A';
			if ( tm.ti_hour > 11 ) {
				c = 'P';
				tm.ti_hour -= 12;
			}
			if ( tm.ti_hour == 0 )  tm.ti_hour = 12;
			AtSayF( x, y, "%2d:%02d %cM",
				tm.ti_hour, tm.ti_min, c );
			break;
	}
}
