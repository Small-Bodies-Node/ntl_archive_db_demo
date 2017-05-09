#include <conio.h>

int CC_PUSHX, CC_PUSHY;

void PushCurpos()
{
	CC_PUSHX = wherex();
	CC_PUSHY = wherey();
}

void PopCurpos()
{
	gotoxy( CC_PUSHX, CC_PUSHY );
}
