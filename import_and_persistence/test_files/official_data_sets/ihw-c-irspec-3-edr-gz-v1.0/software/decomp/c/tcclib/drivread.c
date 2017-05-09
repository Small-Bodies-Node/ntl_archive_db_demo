#include <alloc.h>
#include <dos.h>
#include <ctype.h>
#include "tcclib.h"
#include <conio.h>

#define SECTOR_SIZE 512

int DriveReady (char drv)
{
    int cnt, retry = 1;
    union REGS inregs, outregs;
    struct SREGS segregs;
	unsigned char *buffer;
    char back[600];

    buffer = malloc(SECTOR_SIZE);           /* allocate temp sector buffer */
    cnt = 0;
    while (1) {
        inregs.h.ah = 0x04;                 /* function # */
        inregs.h.al = 1;                    /* # of sectors */
        inregs.h.ch = 0;                    /* 1st cylinder */
        inregs.h.cl = 1;                    /* 1st sector */
        inregs.h.dh = 0;                    /* 1st side */
        inregs.h.dl = toupper(drv) - 'A';   /* drive ('A' = 0) */
        segregs.es = FP_SEG(buffer);        /* ES:BX pt to buffer (for old BIOS's) */
        inregs.x.bx = FP_OFF(buffer);
        int86x(0x13, &inregs, &outregs, &segregs);      /* call Int 13H */

        if (!outregs.h.ah) break;           /* status ok -- return */
        if (++cnt > retry) break;           /* already retried */

        /* otherwise retry -- first issue reset */
        inregs.h.ah = 0x00;                 /* function # */
        inregs.h.dl = toupper(drv) - 'A';   /* drive ('A' = 0) */
        int86(0x13, &inregs, &outregs);     /* call Int 13H */
    }

	free(buffer);

    if ( outregs.h.ah != 0 ) {
        gettext( 22, 8, 57, 14, back );
        BlockErase( 22, 8, 57, 14 );
        ExplodeBox( 24, 9, 55, 13 );
        CenterF( 11, "Drive %c is not ready ...", drv );
        Center( 13, " Press any key ... " );
        ChangeBlock( 24, 9, 55, 13, A_REVERSE );
        GComm();
        puttext( 22, 8, 57, 14, back );
    }
    return(!outregs.h.ah);                  /* return TRUE if drive ready */
}
#define SECTOR_SIZE 512
