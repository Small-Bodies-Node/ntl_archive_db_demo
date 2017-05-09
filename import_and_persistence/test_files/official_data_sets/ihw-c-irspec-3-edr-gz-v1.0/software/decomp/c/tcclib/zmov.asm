name    ZMOV

ZMOV_TEXT	segment	byte public 'CODE'

assume  cs:ZMOV_TEXT,ds:Nothing

; void far if0move(char far *dest, char far *src, unsigned len);
;   Replace len zero dest words with corresponding src words
PUBLIC _if0move
_if0move	proc	far
	push	bp
	mov	bp,sp
        push    ds
        push    es
        push    di
        push    si

        ; Load the pointers
        lds     si, dword ptr [bp+10] ;source
        les     di, dword ptr [bp+6]  ;destination
        mov     cx, word ptr  [bp+14] ;number of bytes to move

        shr     cx,1                  ; Convert CX to words

        cld

        ; Move the data.
MovLoopZm:
        cmp     word ptr es:[di],0    ;test to see if it is zero
        jne     SimMovZm              ;if it is not, skip move

        movsw                         ;move a word over it

        loop    MovLoopZm             ;loop to next byte

        jmp     short RetZm           ;done moving
        
SimMovZm:
        inc     di                    ;simulate move
        inc     di
        inc     si
        inc     si
        loop    MovLoopZm

RetZm:
        pop     si
        pop     di
        pop     es
        pop     ds
	pop	bp
	ret	
_if0move	endp


; void far ifn0move(char far *dest, char far *src, unsigned len);
;    Replace len dest words with corresponding non-zero src words
PUBLIC _ifn0move
_ifn0move       proc	far
	push	bp
	mov	bp,sp
        push    ds
        push    es
        push    di
        push    si

        ; Load the pointers
        lds     si, dword ptr [bp+10] ;source
        les     di, dword ptr [bp+6]  ;destination
        mov     cx, word ptr  [bp+14] ;number of bytes to move

        shr     cx,1                  ; Convert to words

        cld

        ; Move the bytes.
LoopNzm:
        cmp     word ptr ds:[si],0    ;Check for Zero
        je      SimMoveNzm            ;do not move a zero
        movsw                         ;move the words
        loop    LoopNzm
        jmp     short RetNzm

SimMoveNzm:
        inc     si                    ;simulate move
        inc     si
        inc     di
        inc     di
        loop    LoopNzm

RetNzm:
        pop     si
        pop     di
        pop     es
        pop     ds
	pop	bp
	ret	
_ifn0move	endp

; void far strtoscrn(char far *dest, char far *src, char attr, unsigned len)
;   Write upto len char/attr pairs from src and attr to dest.
PUBLIC _strtoscrn
_strtoscrn      proc    far
        push    bp
        mov     bp,sp
        push    ds
        push    es
        push    si
        push    di

        ; Load the pointers
        lds     si, dword ptr [bp+10] ;source
        les     di, dword ptr [bp+6]  ;destination
        mov     ah, byte ptr  [bp+14] ;Attribute
        mov     cx, word ptr  [bp+16] ;max. number of bytes to move

        or      cx,cx
        jz      RetSts

MovLoopSts:
        lodsb
        or      al,al
        jz      short RetSts
        stosw
        loop    MovLoopSts

RetSts:
        pop     di
        pop     si
        pop     es
        pop     ds
        pop     bp
        ret
_strtoscrn      ENDP

; void far chartoscrn(char far *dest, char ch, char attr, unsigned len)
;   Write upto len char/attr pairs from src and attr to dest.
PUBLIC _chartoscrn
_chartoscrn     proc    far
        push    bp
        mov     bp,sp
        push    ds
        push    di

        ; Load the pointers
        les     di, dword ptr [bp+6]  ;destination
        mov     al, byte ptr [bp+10]  ; Char
        mov     ah, byte ptr [bp+12]  ; Attr
        mov     cx, word ptr [bp+14]  ;max. number of bytes to move

        ; Move the bytes
    rep stosw

        pop     di
        pop     ds
        pop     bp
        ret
_chartoscrn      ENDP


ZMOV_TEXT       ENDS

END

