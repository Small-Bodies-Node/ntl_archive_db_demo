#include <mem.h>
#include <stdio.h>

char *RevJul( int jul )
{
    static int days[13] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    static char date[11];
    int days_year;
    int n = 1;
    int year;

    year = 80;
    memset(date, 0, sizeof(date));
    days[2] = 28;
    do {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            days_year = 366;
        else
            days_year = 365;
        year++, jul -= days_year;
    } while (jul > 0);
    year--, jul += days_year;
    if (days_year == 366)
        days[2] = 29;
    do
        jul -= days[n++];
    while (jul > 0);
    --n, jul += days[n];

    if ( n < 0 || n > 12 )
        return( "00-00-00" );

    if ( jul < 0 || jul > 31 )
        return( "00-00-00" );

    if ( year < 0 || year > 99 )
        return( "00-00-00" );

    sprintf(date, "%02d-%02d-%02d", n, jul, year);
    return (date);
}
