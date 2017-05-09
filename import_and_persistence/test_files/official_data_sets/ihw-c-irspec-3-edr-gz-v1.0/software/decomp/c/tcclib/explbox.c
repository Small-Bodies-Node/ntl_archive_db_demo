void BlockErase( int x, int y, int xx, int yy );
void XDrawBox( int x, int y, int xx, int yy );

void ExplodeBox( int x, int y, int xx, int yy )
{
	register int i;
	int midx1, midy1, midx2, midy2;
	int num = 0;
	int flag;
	int Inc = 7000;

	midx1 = (xx + x) / 2 - 1;
	midy1 = (yy + y) / 2 - 1;
	midx2 = midx1 + 1;
	midy2 = midy1 + 1;

	XDrawBox( midx1, midy1, midx2, midy2 );
	flag = 0;
	while ( num < 4 ) {
		num=0;
		if (flag == 1)
			flag = 0;
		else
			flag = 1;

		if ( midx1 > x )
			midx1--;
		else
			num++;

		if ( midx2 < xx )
			midx2++;
		else
			num++;

		if ( midy1 > y && flag > 0 )
			midy1--;
		else
			num++;

		if ( midy2 < yy && flag > 0 )
			midy2++;
		else
			num++;

		for (i=0; i<Inc; ++i) ;
		Inc -= 700;
		BlockErase( midx1+1, midy1+1, midx2-1, midy2-1 );
		XDrawBox( midx1, midy1, midx2, midy2 );
	}
	BlockErase( x, y, xx, yy );
	XDrawBox( x, y, xx, yy );
}
