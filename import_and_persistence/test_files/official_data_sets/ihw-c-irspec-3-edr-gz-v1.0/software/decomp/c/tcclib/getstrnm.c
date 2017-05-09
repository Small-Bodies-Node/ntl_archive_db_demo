#include "tcclib.h"
#include <alloc.h>
#include <conio.h>

int select ( char *menu[], int items, int x1, int y1, int x2 );

int getstringnum ( char *menu[], int items, int x1, int y1, int x2, int y2 )
{
    int x, y, selection;
    int xborder = 2, yborder = 1;
    char *buffer;

    /* 1. Display pop-up menu */
    if ((buffer = calloc ((x2-x1+1)*(y2-y1+1)*2, sizeof (char))) == NULL )
        return (-1);
    gettext (x1, y1, x2, y2, buffer);
    BlockErase (x1, y1, x2, y2);
    DrawBox (x1, y1, x2, y2);
    for ( y = y1 + yborder, x = 0; y < y2 && x < items; ++x, ++y ) {
		AtSay (x1+xborder, y, menu[x]);
    }

    /* 2. Obtain selection */
    selection = select (menu, items, x1 + xborder - 1, y1 + yborder, x2 - xborder + 1);

    /* 3. Erase pop-up menu and restore screen */
    puttext (x1, y1, x2, y2, buffer);
    free (buffer);

    return (selection);
}

