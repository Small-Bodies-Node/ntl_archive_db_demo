#include <stdlib.h>
#include "window.h"
#include "_window.h"

char *backimage;
windowtype *imagefor;

void invalidate_backimage(void)
{
	if ( backimage )
	{
		free(backimage);
		backimage=NULL;
	}
}
