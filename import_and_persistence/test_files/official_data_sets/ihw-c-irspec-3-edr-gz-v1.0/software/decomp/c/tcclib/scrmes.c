#include <stdio.h>
#include <string.h>
#include <conio.h>
#include "tcclib.h"

void ScrollMessage (char *msg)
{
    char s1[999], s2[250]={"Press any key ..."}, s3[150], s4[150];
    char scr[1200];
    register int i, j;

    gettext( 3, 8, 77, 14, scr );
    BlockErase( 3, 8, 77, 14 );
    ExplodeBox( 5, 9, 76, 13 );
    strcpy( s1, msg );

    i = ( 70 - strlen( s1 ) ) / 2;
    while ( i-- ) {
        strcpy( s3, s1 );
        sprintf( s1, " %s", s3 );
    }
    i = strlen( s1 );
    while ( i-- ) strcat( s1, " " );

    i = ( 70 - strlen( s2 ) ) / 2;
    while ( i-- ) {
        strcpy( s4, s2 );
        sprintf( s2, " %s", s4 );
    }
    i = strlen( s2 );
    while ( i-- ) strcat( s2, " " );

    sprintf( s3, "                                  %s", s1 );
    strcpy( s1, s3 );
    sprintf( s4, "%s                                  ", s2 );
    strcpy( s2, s4 );

    for (i=1; i<36; ++i) {
        strncpy( s3, s1+i, 70 );
        strncpy( s4, s2+(70-i-34), 70 );
        s3[70] = 0;
        s4[70] = 0;
        AtSay( 6, 10, s3 );
        AtSay( 6, 12, s4 );
        for (j=0; j<3000; ++j) ;
    }

    GComm();
    puttext( 3, 8, 77, 14, scr );
}
