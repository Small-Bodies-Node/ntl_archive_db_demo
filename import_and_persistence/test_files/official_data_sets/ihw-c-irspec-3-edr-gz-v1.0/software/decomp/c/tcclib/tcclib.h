typedef struct {
	char  *Item;
	char  *Desc;
	int    FuncKey;
	int    (*func)( void );
} MenuRec;

typedef struct {
             char Name[9];
             char Ext[4];
             char Attribute;
    unsigned int  Date;
    unsigned int  Time;
    unsigned long Size;
             int  Tag;
} FileStruc;

typedef struct {
    int   x;
    int   y;
    int   Len;
    int   Type;
    char *Address;
    int   EditFlag;
    int   NumDecimals;
} FieldStruc;

typedef struct {
    char Name[13];
} FileNameStruc;

extern unsigned char attrib;
extern unsigned char A_REVERSE;
extern unsigned char A_NORMAL;

#define F_PTR  0
#define F_INT  1
#define F_INT0 2
#define F_CHAR 3
#define F_LNG  4
#define F_LNG0 5
#define F_DBL  6
#define F_DATE 7
#define F_BLN  8
#define F_FLT  10

#define UnsFar unsigned char far

void       Accept( int x, int y, char *Prompt, char *Buffer, int Length );
int        AllBlanks( register char *cp );
void       AtSay( int col, int row, register char *cp );
void       AtSayA( int col, int row, unsigned char attrib, register char *cp );
void       AtSayF( int col, int row, char *fmt, ... );
void       AtSayFA( int col, int row, unsigned char attrib, char *fmt, ... );
void       Beep( void );
unsigned   Bit( int x );
void       BlockErase( int x, int y, int xx, int yy );
void       CapsLock( int flag );
void       Center( int y, char *s );
void       CenterA( int y, unsigned char attrib, char *s );
void       CenterF( int y, char *fmt, ... );
void       CenterFA( int y, unsigned char attrib, char *fmt, ... );
void       ChangeBlock( int x, int y, int xx, int yy, char attrib );
int        CheckPrn( void );
char      *ClearBeg( register char *cp );
void       ClearBuf( void );
void       ClearCRLF( char *cp );
char      *ClearEnd( register char *cp );
void       ClearField( int x, int y, int len );
int        CompareFileNames( FileNameStruc *A, FileNameStruc *B );
int        CopyFile( char *src, char *dest );
UnsFar    *CurrentPos( void );
void       CursorOff( void );
void       CursorOn( void );
void       DrawBox( int x, int y, int xx, int yy );
void       DrawBox1( int x, int y, int xx, int yy );
int        Exists( char *filename );
void       ExplodeBox( int x, int y, int xx, int yy );
void       ExplodeBox1( int x, int y, int xx, int yy );
char      *FileName( FileStruc *fp );
long       FileSize( char *filename );
int        FindDown( FieldStruc *Field, int pos, int NUMFIELDS );
int        FindLeft( FieldStruc *Field, int pos, int NUMFIELDS );
int        FindRight( FieldStruc *Field, int pos, int NUMFIELDS );
int        FindUp( FieldStruc *Field, int pos, int NUMFIELDS );
int        GComm( void );
void       GCommBackground( void (*funct)( void ) );
int        GCommCheck( int Key );
void       GCommLink ( int key, void (*funct)( void ) );
void       GCommUnlink (int key);
void       GetAllFields( FieldStruc *Field, int NUMFIELDS,
           int (*ChHnd)( int ch ), void (*Update)( void ) );
void       GetCursor( int *Top, int *Bottom );
unsigned   GetDate( unsigned start_date );
double     GetDouble( void );
int        GetField( FieldStruc *Field );
int        GetFieldA( int x, int y, int len, int type, char *address );
char      *GetFile( int x, int y, int xx, int yy, char *Mask,
           int FileAttrib, int ExtOn );
int        GetInt( void );
int        GetLine( char *ptr, int dsize, int start );
long       GetLong( void );
int        GetRec( int filehandle, void *buffer, int sizeofrec, long fileptr );
void       GetScreen( char *buffer );
int        GetVidMode( void );
int        GetYN(char *s);
void       HLin( int x, int y, int xx, int yy );
void       HLin1( int x, int y, int xx, int yy );
void       HideCursor( void );
void       HorzMenu( MenuRec *MenuPtr, int NumChoices, int x, int y,
           int xx, int yy );
int        IsAT( void );
int        IsCGA( void );
int        IsEGA( void );
int        IsMONO( void );
unsigned   Julian( char *datestr );
void       MakeBox( char *s );
long       MaxRAM( void );
void       NPrint( int num, char *cp );
void       NPrintA( int num, int attrib, char *cp );
void       NPrintF( int num, char *fmt, ... );
void       NPrintFA( int num, int attrib, char *fmt, ... );
void       NormalText( void );
void       NumLock( int flag );
void       OutChar( unsigned char c );
void       OutCharA( unsigned char attrib, unsigned char c );
void       PopCurpos( void );
void       PopScreen( void );
void       PushCurpos( void );
int        PushScreen( void );
void       PutCursor( int Top, int Bottom );
void       PutDate( int x, int y, int format );
void       PutField( FieldStruc *Field );
void       PutFree( int x, int y, char *format );
int        PutRec( int filehandle, void *buffer, int sizeofrec, long fileptr );
void       PutScreen( char *buffer );
void       PutTime( int x, int y, int format );
void       RepChar( int times, unsigned char c );
void       RepCharAttr( int times, unsigned char attrib, unsigned char c );
char      *RevJul( unsigned date );
void       ReverseText( void );
void       Say( register char *cp );
void       SayA( unsigned char attrib, register char *cp );
void       SayF( char *fmt, ... );
void       SayFA( char attrib ,char *fmt, ... );
FileStruc *ScanDir( char *Mask, int Type );
int        ScanTree( char *path, char *filespec, int (*FN)(FileStruc *f,
           char *p) );
int        ScrAttr( void );
int        ScrChar( void );
UnsFar    *ScrPtr( int col, int row );
void       ScrollDown( int x, int y, int xx, int yy, int n );
void       ScrollLock( int flag );
void       ScrollMessage(char *msg);
void       ScrollUp( int x, int y, int xx, int yy, int n );
void       SetAttrib( char attribute  );
void       SetVidMode( int mode );
void       StrLeft( char *dest, char *src, int num );
void       StrRight( char *dest, char *src, int num );
void       StrRpl( char *string, int startpos, int num, char *replacestring );
void       TcclibInitialize( void );
double     TimeElapsed( void );
void       TimerStart( void );
unsigned   TodaysDate( void );
void       VLin( int x, int y, int xx, int yy );
void       VLin1( int x, int y, int xx, int yy );
void       VertMenu( MenuRec MP[], int numchoices, int x, int y,
           int xx, int yy );
int        WeekDay( void );
int        WindowLister( int x, int y, int xx, int yy, int CharToQuitOn,
           int *NumItems, int NumToStartWith, int (*CharHandler)(int ch,
           int Index), void (*ScreenClearer)( void ),
           void (*DisplayLineFunction)(int Index) );
void       XDrawBox( int x, int y, int xx, int yy );
void       XDrawBox1( int x, int y, int xx, int yy );

char       getchf( char *list, char defchar );
int        posneg( int num );
int        select(char *menu[], int items, int x1, int y1, int x2);
char      *strdel( char *string, int start, int num );
char      *strins( char *string, int start, char *insstr );
char      *stristr( char *string1, char *string2 );
char      *strreplace(char *string, int start, int num, char *repstr );
char      *strresize( char *string, int newlen );
void       writevid( int x, int y, int x2, char *p, int attrib);


#define BS 8
#define FORMFEED 12
#define CR 13
#define LF 10
#define ESC 27
#define HOME 327
#define END 335
#define UP 328
#define DOWN 336
#define PGUP 329
#define PGDN 337
#define LEFT 331
#define RIGHT 333
#define INS 338
#define DEL 339

#define BS 8
#define F1 315
#define F2 316
#define F3 317
#define F4 318
#define F5 319
#define F6 320
#define F7 321
#define F8 322
#define F9 323
#define F10 324

#define ALT_A 286
#define ALT_B 304
#define ALT_C 302
#define ALT_D 288
#define ALT_E 274
#define ALT_F 289
#define ALT_G 290
#define ALT_H 291
#define ALT_I 279
#define ALT_J 292
#define ALT_K 293
#define ALT_L 294
#define ALT_M 306
#define ALT_N 305
#define ALT_O 280
#define ALT_P 281
#define ALT_Q 272
#define ALT_R 275
#define ALT_S 287
#define ALT_T 276
#define ALT_U 278
#define ALT_V 303
#define ALT_W 273
#define ALT_X 301
#define ALT_Y 277
#define ALT_Z 272

#define CTL_A 1
#define CTL_B 2
#define CTL_C 3
#define CTL_D 4
#define CTL_E 5
#define CTL_F 6
#define CTL_G 7
#define CTL_H 8
#define CTL_I 9
#define CTL_J 10
#define CTL_K 11
#define CTL_L 12
#define CTL_M 13
#define CTL_N 14
#define CTL_O 15
#define CTL_P 16
#define CTL_Q 17
#define CTL_R 18
#define CTL_S 19
#define CTL_T 20
#define CTL_U 21
#define CTL_V 22
#define CTL_W 23
#define CTL_X 24
#define CTL_Y 25
#define CTL_Z 26

#define TAB 9
#define BACKTAB 271
#define SHFT_TAB 271

#define SHFT_F1 340
#define SHFT_F2 341
#define SHFT_F3 342
#define SHFT_F4 343
#define SHFT_F5 344
#define SHFT_F6 345
#define SHFT_F7 346
#define SHFT_F8 347
#define SHFT_F9 348
#define SHFT_F10 349

#define CTL_F1 350
#define CTL_F2 351
#define CTL_F3 352
#define CTL_F4 353
#define CTL_F5 354
#define CTL_F6 355
#define CTL_F7 356
#define CTL_F8 357
#define CTL_F9 358
#define CTL_F10 359

#define CTL_HOME  327
#define CTL_END   335
#define CTL_PGUP  339
#define CTL_PGDN  337
#define CTL_LEFT  372
#define CTL_RIGHT 371
#define CTL_UP    328
#define CTL_DOWN  336

#define ALT_F1 360
#define ALT_F2 361
#define ALT_F3 362
#define ALT_F4 363
#define ALT_F5 364
#define ALT_F6 365
#define ALT_F7 366
#define ALT_F8 367
#define ALT_F9 368
#define ALT_F10 369

#define ALT_1 376
#define ALT_2 377
#define ALT_3 378
#define ALT_4 379
#define ALT_5 380
#define ALT_6 381
#define ALT_7 382
#define ALT_8 383
#define ALT_9 384
#define ALT_0 385
