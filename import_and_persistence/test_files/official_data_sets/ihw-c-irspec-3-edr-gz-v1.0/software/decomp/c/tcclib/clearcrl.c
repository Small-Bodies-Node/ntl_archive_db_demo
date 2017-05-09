
void ClearCRLF( char *s )
{
    register char *cp;

    cp = s;
    while ( *cp > 31 )
        cp++;
    *cp = 0;
}
