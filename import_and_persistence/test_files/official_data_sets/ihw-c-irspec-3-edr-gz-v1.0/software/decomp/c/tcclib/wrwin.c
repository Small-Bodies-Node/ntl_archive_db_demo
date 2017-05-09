#include <stdlib.h>
#include <string.h>
#include <conio.h>
#include <assert.h>
#include <mem.h>
#include "window.h"
#include "_window.h"

int winwrite(windowtype *window, char *string)
{
	int linelen,pos,strln,rtn,i,size;

	/* Only background write to a window in the background */
	if ( window->previous == NULL )
	{
		cputs(string);
		return 0;
	}

	if ( !backimage || imagefor != window )
	{
		invalidate_backimage();
		if ( (backimage = getwinimage(window,1)) == NULL )
			return -1;
		imagefor = window;
	}

	linelen = (window->right-window->left)-1;
	pos = (window->ypos-1) * linelen + (window->xpos-1);
	strln = strlen(string);
	i = 0;
	size = linelen * (window->bottom-window->top-1);
	while ( pos + strln > size )
	{
		int t;
		strtoscrn(&backimage[pos << 1],&string[i], window->attr,
			t = size - pos);
		i   	+= t;
		strln   -= t;
		pos 	+= t - linelen;

		/* Scroll window */
		memmove(backimage, &backimage[linelen << 1], (size - linelen) << 1);
		chartoscrn(&backimage[(size - linelen) << 1], ' ', window->attr,
			linelen);
	}
	strtoscrn(&backimage[pos << 1], &string[i], window->attr, strln);
	pos += strln;

	if ( (rtn = putwinimage(window, backimage, 1, 1)) != 0 )
		return rtn;

	/* update cursor position */
	window->xpos = (pos % linelen) + 1;
	window->ypos = (pos / linelen) + 1;

	return 0;
}
