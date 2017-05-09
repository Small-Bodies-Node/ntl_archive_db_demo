#include <stdio.h>
#include <time.h>

unsigned Julian( char *datestr );

unsigned TodaysDate()
{
    struct tm *st, *localtime();
    long prsnt_time;
    char tim[10];

    time( &prsnt_time );
    st = localtime( &prsnt_time );
    sprintf( tim, "%02d-%02d-%02d", st->tm_mon + 1, st->tm_mday, st->tm_year );
    return( Julian( tim ) );
}
