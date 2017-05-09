#include <conio.h>
#include <alloc.h>
#include <mem.h>
#include <string.h>
#include <assert.h>
#include "window.h"
#include "_window.h"

windowtype *windowchain = NULL;
static unsigned int xpos, ypos;

enum {TopLine, BottomLine, TopLeft, TopRight, BotLeft, BotRight,
		RightLine, LeftLine } _box;

static unsigned char style[][8] =
	{ 	{ '-',	'-',	'+'   , '+'   , '+'   , '+'   , '|',	'|'	},
		{ '\xC4', '\xC4', '\xDA', '\xBF', '\xC0', '\xD9', '\xB3', '\xB3' },
		{ '\xCD', '\xCD', '\xC9', '\xBB', '\xC8', '\xBC', '\xBA', '\xBA' },
		{ '\xC4', '\xCD', '\xDA', '\xB7', '\xD4', '\xBC', '\xB3', '\xBA' }
		};


extern enum windowerrtype windowerr = WE_OK;

/* box() --------------------------------------------------------
 *   Routine to draw a box around a window.
 * --------------------------------------------------------------
 */
static void box(	unsigned char left,
					unsigned char top,
				 	unsigned char right,
					unsigned char bottom,
							 char pattr,
					unsigned char *boxchars)
{
	unsigned int tmp1, tmp2, attr = pattr << 8;
	int width, size, i;
	unsigned int *box;

	if ( (box = malloc( (size = (right-left+1)*(bottom-top+1))*2 )) == NULL )
		return;

	box[0] =  ' ' | attr;

	/* Ripple move the space with current attribute to the entire block */
	/* This routine depends on memcpy not copying overlapping blocks
	 * correctly in the forward direction and two bytes at a time.
	 */
	memcpy(&box[1],box,size<<1);

	/* Top line */
	box[0] = boxchars[TopLeft] | attr;
	box[ ((width = right - left + 1) - 1) ] = boxchars[TopRight] |
		attr;

	box[1] = boxchars[TopLine] | attr;

	/* Ripple move - see above */
	memcpy(&box[2],&box[1],(width-3)<<1);

	tmp1 = boxchars[LeftLine]  | attr;
	tmp2 = boxchars[RightLine] | attr;
	for (i = width; i<size-width; i += width)
	{
		box[i+width-1] 	= tmp1;
		box[i] 			= tmp2;
	}

	box[i] = boxchars[BotLeft] | attr;
	box[i+width-1] = boxchars[BotRight] | attr;

	box[i+1] = boxchars[BottomLine] | attr;

	/* Ripple move - see above */
	memcpy(&box[i+2],&box[i+1],(width-3)<<1);
	puttext(left,top,right,bottom,box);

	free(box);

}

/* makewindow() ---------------------------------------------------
 *   Open a window on the screen.  It becomes the current window.
 *   RETURNS: 0 Success, 1 Out of memory, 2 Bad coordinates.
 *   Coordinates must range from 2-79,2-24 (allow for the border).
 *   Styles: 0 use not graphics chars, 1 single line, 2 double line
 * ----------------------------------------------------------------
 */
windowtype *makewindow(	unsigned char  left,
				   		unsigned char  top,
				   		unsigned char  right,
						unsigned char  bottom,
						char 		   attr,
						unsigned char  the_style,
						char		  *title)
{
	windowtype *current;
	int size, len;

	windowerr = WE_OK;

	invalidate_backimage();

	/* Check the coordinates */
	if (top < 2 || bottom > 24 || left < 2 || right > 79 || top > bottom
		|| left > right)
	{
		windowerr = WE_BADC;
		return NULL;
	}

	/* Allocate the memory for the window */
	if ( (current = malloc(sizeof(windowtype))) == NULL )
	{
		windowerr = WE_OMEM;
		return NULL;
	}

	/* Store the coorinates (including the border) */
	current->top	= --top;
	current->bottom	= ++bottom;
	current->left 	= --left;
	current->right	= ++right;

	/* Calculate the size of the back-buffer */
	current->size = size = ((bottom-top+1)*(right-left+1)) << 1;

	/* Allocate the memory for the back buffer */
	if ( (current->backbuffer = malloc(size)) == NULL )
	{
		windowerr = WE_OMEM;
		return NULL;
	}

	/* Store the cursor position for the current window */
	if (windowchain)
	{
		windowchain->xpos = wherex();
		windowchain->ypos = wherey();
	}
	else
	{
		xpos = wherex();
		ypos = wherey();
	}

	/* Store the screen image that we are going to write over */
	gettext(left,top,right,bottom,current->backbuffer);

	/* Draw the border */
	window(1,1,80,25);

	box(left,top,right,bottom,attr,style[the_style]);

	/* Set the writting attribute */
	textattr(attr);
	current->attr = attr;

	/* Center the title on the border */
	/*  If the title is too big for the border or it
	 *  is empty, don't write it
	 */
	if ( (len = strlen(title)) <= (right - left)   || len == 0 )
	{
		gotoxy( (right + left - len + 1) >> 1, top);
		cputs(title);
	}

	/* Section of the screen for the window */
	window(left+1,top+1,right-1,bottom-1);


	/* Register the window in the window chain, it becomes current */
	if (windowchain != NULL)
		windowchain->previous = current;
	current->next = windowchain;
	current->previous = NULL;
	windowchain = current;

	/* Return the window */
	return current;
}

/* deletewindow() ----------------------------------------------
 *	Delete a window.
 *	RETURN: 0 Success, -1 Failure.
 * -------------------------------------------------------------
 */
int deletewindow(windowtype *current)
{
	if (windowchain != current)
		return -1;

	invalidate_backimage();

   	puttext(current->left,current->top,current->right,current->bottom,
		current->backbuffer);

	windowchain = current->next;
	free(current->backbuffer);
	free(current);
	if (windowchain != NULL)
		windowchain->previous = NULL;

	if (windowchain)
	{
		window(windowchain->left+1,windowchain->top+1,windowchain->right-1,
			   windowchain->bottom-1);
		gotoxy(windowchain->xpos, windowchain->ypos);
	}
	else
	{
		window(1,1,80,25);
		gotoxy(xpos,ypos);
	}
	return 0;
}
