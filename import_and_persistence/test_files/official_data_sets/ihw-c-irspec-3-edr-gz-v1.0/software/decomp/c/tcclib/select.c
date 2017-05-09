#include "tcclib.h"
#include <dos.h>
#include <stdio.h>
#include <bios.h>
#include <conio.h>
#include <ctype.h>

void writevid ( int x, int y, int x2, char *p, int attrib)
{
    union REGS r;
    register int i;

    for ( i = x; i<=x2; i++ ) {
        gotoxy (i, y);
        r.h.ah = 9;
        r.h.bh = 0;
        r.x.cx = 1;
        r.h.al = (*p && i != x)? *p++ : ' ';   /* Highlights entire bar */
        r.h.bl = attrib;
        int86 (0x10, &r, &r);
    }
}

int select (char *menu[], int items, int x1, int y1, int x2)
{
    union inkey {
        char ch[2];
        int i;
    } c;
    register int arrow = 0, x;

	writevid (x1, y1, x2, menu[0], A_REVERSE); /* highlight */
    for (;;) {
        while (!bioskey(1)) continue;
        c.i = bioskey (0);

		writevid (x1, y1 + arrow, x2, menu[arrow], A_NORMAL);

        if (c.ch[0]) {
            switch (c.ch[0]) {
                case '\r': return (arrow);
                case ' ':  ++arrow; break;
                case 27:   return (-1);
                default:
                    for ( x = arrow + 1; x != arrow; ++x ) {
                        if ( x == items ) x = -1;
                        else if ( toupper (c.ch[0]) == toupper (menu[x][0]) )
                            arrow = x--;
                    }
                    if ( toupper (c.ch[0]) != toupper (menu[x][0]) )  putc (7, stdout);
                    break;
            }
        }
        else {
            switch (c.ch[1]) {
                case 72: case 75: --arrow; break;
                case 80: case 77: ++arrow; break;
                default: putc (7, stdout); break;
            }
        }

        if ( arrow == items ) arrow = 0;
        if ( arrow < 0 ) arrow = items - 1;
		writevid (x1, y1+arrow, x2, menu[arrow], A_REVERSE);
    }
}
