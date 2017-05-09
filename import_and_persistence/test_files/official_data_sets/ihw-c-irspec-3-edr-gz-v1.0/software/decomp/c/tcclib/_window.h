/* _window.h -------------------------------------------------------
 *		Internal header file for internal functions.
 * -----------------------------------------------------------------
 */

extern windowtype *windowchain;
extern char *backimage;
extern windowtype *imagefor;

void invalidate_backimage(void);
char *getwinimage(windowtype *window, unsigned off);
int putwinimage(windowtype *window, char *image, unsigned off, int update);

extern void far if0move(char far *dest, char far *src, unsigned len);
extern void far ifn0move(char far *dest, char far *src, unsigned len);
extern void far strtoscrn(char far *dest, char far *src, char attr,
	unsigned len);
extern void far chartoscrn(char far *dest, char ch, char attr,
	unsigned len);
