#include <stdlib.h>
#include <conio.h>
#include <mem.h>
#include "window.h"
#include "_window.h"

static char *forimage;

void processimage(windowtype *window, char *image, unsigned inset,
	int put)
{
	register unsigned t1,t2;
	windowtype *current = window->previous;
	unsigned top, bottom, left, right;
	unsigned off1, off2, len, t;

	while ( current )
	{
		if (
		(top	= ((t1=window->top+inset)	> (t2=current->top)	? t1 : t2)) <=
		(bottom = ((t1=window->bottom-inset) < (t2=current->bottom) ? t1 : t2)) &&
		(left   = ((t1=window->left+inset)   > (t2=current->left)   ? t1 : t2)) <=
		(right  = ((t1=window->right-inset)  < (t2=current->right)  ? t1 : t2)))
  		{
			/* Update the forscreen and backscreen 	  */
			/*   Calculate optimized index references */
			off1 = ((window->right-inset) - (window->left+inset) + 1) << 1;
			off2 = ((current->right) - (current->left) + 1) << 1;
			len = (right - left + 1) << 1;

			/* calculate move positions */
			t1 = (left - (window->left+inset)) << 1;
			t2 = (left - (current->left)) << 1;

			if ( (window->top+inset) == top)
				t2 += off2*(top - (current->top));
			else
				t1 += off1*(top - (window->top+inset));

			/*   Move the memory appropriately */
			for (t=top ; t <= bottom; t++)
			{
				if (put)
				{
					char *tmp = &image[t1];
					ifn0move(&current->backbuffer[t2],tmp,len);
					memset(tmp,'\0',len);
				}
				else
					if0move(&image[t1],&current->backbuffer[t2],len);
				t1 += off1;
				t2 += off2;
			}
		}
		current = current->previous;
	}
}

char *getwinimage(windowtype *window, unsigned off)
{
	char *image;

	free(forimage);
	forimage = NULL;

	if ( (image = calloc(window->size,1)) == NULL )
		return NULL;

	if ( (forimage = malloc(window->size)) == NULL )
	{
		free(image);
		return NULL;
	}
	processimage(window, image, off, 0);

	gettext(window->left+off, window->top+off, window->right-off,
		window->bottom-off,forimage);
	if0move(image, forimage, window->size);

	return image;
}

int putwinimage(windowtype *window, char *image, unsigned off, int update)
{
	if ( !forimage )
	{
		if ( (forimage = malloc(window->size)) == NULL )
			return -1;
		gettext(window->left+off, window->top+off, window->right-off,
			window->bottom-off,forimage);
	}

	processimage(window, image, off, 1);

	ifn0move(forimage, image, window->size);

	if ( update )
		puttext(window->left+off, window->top+off, window->right-off,
			window->bottom-off,forimage);
	else
		memcpy(image, forimage, window->size);

	free(forimage);
	forimage = NULL;

	return 0;
}