#include <conio.h>
#include <alloc.h>
#include <mem.h>
#include <string.h>
#include <assert.h>
#include "window.h"
#include "_window.h"

/* shiftwindow() ---------------------------------------------------
 *	Make a window the current window.
 *	RETURN: 0 Success, 1 Out of memory
 * -----------------------------------------------------------------
 */
int shiftwindow(windowtype *awindow)
{
	char *forscreen;

	/* If it is already the current window, we don't neeed to shift it */
	if (windowchain == awindow)
		return (int) windowerr = WE_OK;

	/* Store the current window's cursor coordinates */
	windowchain->xpos = wherex();
	windowchain->ypos = wherey();

	/* Get the image to place on the screen */
	if ( (forscreen = getwinimage(awindow,0)) == NULL )
	{
		free(forscreen);
		return (int) windowerr = WE_OMEM;
	}

	/* Distribute the backbuffer */
	if ( putwinimage(awindow,awindow->backbuffer,0,0) != 0 )
		return (int) windowerr = WE_OMEM;

	/* Delete the window from current position and insert it on top */
	if (awindow->next)
		awindow->next->previous = awindow->previous;
	if (awindow->previous)
		awindow->previous->next = awindow->next;

	if (windowchain != NULL )
		windowchain->previous = awindow;
	awindow->previous = NULL;
	awindow->next = windowchain;
	windowchain = awindow;

	/* Write the forscreen to the screen, this will actually "pop-up"
	 * the window.
	 */
	puttext(awindow->left,awindow->top,awindow->right,awindow->bottom,
			forscreen);

	/* Section off the screen for the window and put the cursor back
	 * where it was before the window was made non-current
	 */
	window(awindow->left+1,awindow->top+1,awindow->right-1,awindow->bottom-1);
	gotoxy(awindow->xpos,awindow->ypos);
	textattr(awindow->attr);

	/* Free allocated memory */
	free(forscreen);

	/* Tell the user everythings OK */
	return (int) windowerr = WE_OK;
}
