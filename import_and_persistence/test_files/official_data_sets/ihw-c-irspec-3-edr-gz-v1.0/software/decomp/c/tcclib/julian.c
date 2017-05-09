#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <mem.h>

#define MO 0
#define DAY 1
#define YR 2

#define BAD_DIGIT 0                     /* return error codes                */
#define BAD_MONTH 1                     /* means that the dates              */
#define BAD_DAY 2                       /*  January 1, 2, 3, 4 of the base   */
#define BAD_YEAR 3                      /*  year become invalid dates        */
#define BAD_DATE -1                     /* an error code    */

unsigned int Julian( char *date )
{
    static int days[13] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    char c;
    int n = 0;
    int mdy[3];
    unsigned int retjul;
    int base;

    base = 1980;
    days[2] = 28;
    mdy[DAY] = mdy[MO] = mdy[YR] = 0;
    while ( ( c = *date++ ) != 0 ) {
        if (c == ' ')
            continue;
        if (c == '-' || c == '/') {
            n++;
            continue;
        }
        if (!isdigit(c))
            return (BAD_DIGIT);
        mdy[n] = 10 * mdy[n] + (c - '0');
    }
    if (mdy[MO] < 1 || mdy[MO] > 12)
        return (BAD_MONTH);
    if (mdy[YR] < 100) {
        if (mdy[YR] < base - 1900)
            mdy[YR] += 2000;
        else
            mdy[YR] += 1900;
    }
    if (mdy[YR] < base)
        return (BAD_YEAR);
    if (mdy[YR] % 4 == 0 && mdy[YR] % 100 != 0 || mdy[YR] % 400 == 0)
        days[2] = 29;
    if (mdy[DAY] < 1 || mdy[DAY] > days[mdy[MO]])
        return (BAD_DAY);
    retjul = mdy[DAY];
    for (n = 1; n < mdy[MO]; n++) {
        retjul += days[n];
    }
    for (n = base; n < mdy[YR]; n++) {
        if (n % 4 == 0 && n % 100 != 0 || n % 400 == 0)
            retjul += 366;
        else
            retjul += 365;
    }
    return (retjul);
}
