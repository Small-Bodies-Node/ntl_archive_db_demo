#include <string.h>

width ( char *array[], int items )
{
    register int x, longest = 0;
    int tmp;

    for ( x = 0; x < items; ++x ) {
        if ( (tmp = strlen (array[x])) > longest ) longest = tmp;
    }

    return (longest);
}

