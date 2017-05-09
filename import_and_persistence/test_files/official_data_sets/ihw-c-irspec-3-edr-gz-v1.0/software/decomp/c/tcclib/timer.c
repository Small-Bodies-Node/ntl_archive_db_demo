#include <dos.h>

double CC_TIME;

double TimeElapsed( void )
{
	struct time tm;
	double t;

	gettime( &tm );

	t = (double) 3600 * tm.ti_hour +
		(double)   60 * tm.ti_min  +
		(double)  100 * tm.ti_sec  +
		(double) 0.01 * tm.ti_hund;

	return( t - CC_TIME ) ;
}

void TimerStart( void )
{
	struct time tm;

	gettime( &tm );

	CC_TIME = (double) 3600 * tm.ti_hour +
			  (double)	 60 * tm.ti_min  +
			  (double)  100 * tm.ti_sec  +
			  (double) 0.01 * tm.ti_hund;
}
