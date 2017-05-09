/* window.h - Windows header file */

extern enum windowerrtype
{
	WE_OK,		/* No error has occured */
	WE_OMEM,	/* Out of memory   */
	WE_BADC		/* Bad coordinates */
} windowerr;

typedef struct _windowtype
{
	unsigned char 	top,
					left,
					bottom,
					right; 		 /* Coordinates, including box */
	char 			*backbuffer; /* Contents of the screen behind the windowtype */
	unsigned char 	xpos,
					ypos;
	unsigned 		size;
	struct _windowtype *next;		/* Next windowtype */
	struct _windowtype *previous;	/* Previous windowtype */
	char			attr;
} windowtype;

windowtype *makewindow(	unsigned char left, unsigned char top,
						unsigned char right, unsigned char bottom,
						char attr, unsigned char the_style, char *title);
int deletewindow(windowtype *current);
int shiftwindow(windowtype *awindow);
int winwrite(windowtype *window, char *string);

#ifndef NDEBUG
int sum(int *array, int len);
int checklist(void);
void _update_check(void);
#define update_check() _update_check()
#else
#define update_check()
#endif

#define EXPLODE 1
#define POP_UP  0


