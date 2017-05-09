#include <time.h>

int WeekDay()
{
    struct tm  *timeptr;
    time_t      secsnow;

    time( &secsnow );
    timeptr = localtime( &secsnow );
    return( timeptr->tm_wday );
}
