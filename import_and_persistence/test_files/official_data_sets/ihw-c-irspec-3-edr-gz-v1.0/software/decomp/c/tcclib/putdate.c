#include <dos.h>

void AtSayF( int x, int y, char *ftm, ... );
void TcclibInitialize( void );

extern char *CCMONTHS[12];

void PutDate( int x, int y, int format )
{
/*
	formats:  1 = mm/dd/yy
			  2 = mmm dd, yyyy
			  3 = dd-mmm-yy
			  4 = mmm yy
*/
	struct date dt;

	TcclibInitialize();
	getdate( &dt );
	switch( format ) {
		case 1:
			AtSayF( x, y, "%2d/%02d/%02d", dt.da_mon, dt.da_day, dt.da_year % 100 );
			break;
		case 2:
			AtSayF( x, y, "%3s %2d, %04d", CCMONTHS[dt.da_mon], dt.da_day, dt.da_year );
			break;
		case 3:
			AtSayF( x, y, "%2d-%3s-%02d", dt.da_day, CCMONTHS[dt.da_mon], dt.da_year % 100 );
			break;
		case 4:
			AtSayF( x, y, "%3s, %02d", CCMONTHS[dt.da_mon], dt.da_year % 100 );
			break;
	}
}
