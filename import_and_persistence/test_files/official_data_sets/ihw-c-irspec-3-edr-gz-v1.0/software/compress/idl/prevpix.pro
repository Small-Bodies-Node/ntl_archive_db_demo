function prevpix,image                                                        
;+                                                                            
; NAME:                                                                       
;   PREVPIX                                                                   
; PURPOSE:                                                                    
;   Perform previous pixel (first-differences) compression on an              
;   INTEGER*2 array and put it into a byte array                              
; CALLING SEQUENCE:                                                           
;   BYTIM = PREVPIX(IMAGE)                                                    
; INPUT:                                                                      
;   IMAGE - Image array, must be INTEGER*2                                    
; OUTPUT:                                                                     
;   BYTIM - One dimensional BYTE array                                        
; METHOD:                                                                     
;   IMAGE is shifted right by one (16-bit) element, then subtracted           
;   from the original to obtain an array of differences. Absolute             
;   differences less than 128 are represented by the (byte) difference        
;   between pixels. The value 127 is added to each difference to make         
;   the value unsigned. Differences greater than 127 are flagged by           
;   inserting the byte value 255 (FF hex) in the array, followed by the       
;   original 16-bit pixel value, in consecutive bytes. Following FITS         
;   convention, the 16-bit numbers are byte swapped relative to DEC           
;   hardware.                                                                 
; RESTRICTIONS:                                                               
;   Memory hog for large images. Works only on 16-bit integer images.         
; REVISION HISTORY:                                                           
;   Written by A. Warnock (STX), November 29, 1988                            
;-                                                                            
;                                                                             
;    Calculate the difference array DIF                                       
;                                                                             
npts = n_elements(image)        ; # of pixels in IMAGE                        
;                                                                             
;    What precision should DIF be?                                            
;                                                                             
range = abs( long( max(image) ) - long( min(image) ) )                        
if range le 32767 then begin                                                  
   dif = shift(image,1)         ; offset IMAGE by 1 pixel, 16-bit             
endif else begin                                                              
   dif = long( shift(image,1) )        ; offset IMAGE by 1 pixel, 32-bit      
endelse                                                                       
;                                                                             
;    Where are the big differences?                                           
;                                                                             
dif    = image - dif                 ; calculate difference array             
dif(0) = 0                           ; ignore 1st difference                  
bad    = where( abs(dif) ge 128 )    ; list of indices for big diffs          
                                                                              
; !ERR does not mean the same in UNIX...                                      
;nbad   = !err                       ; # of big diffs                         
nbad   = n_elements(bad)                                                      
                                                                              
;                                                                             
;    Create the OUT array                                                     
;                                                                             
nout   = npts + nbad*2 + 1           ; size of output array                   
out    = bytarr( nout )              ; create output array                    
;                                                                             
;    Print out summary statistics on the compression                          
;                                                                             
print,' '                                                                     
print,'Image maximum = ', max(image)                                          
print,'Image minimum = ', min(image)                                          
print,'Image dynamic range = ', range                                         
print,' '                                                                     
print,'There were ', nbad, ' large differences out of ', $                    
    npts, ' pixels.'                                                          
print,' '                                                                     
                                                                              
; vax                                                                         
;print,'$(a,f5.1)',' Compression % (output/input) = ', $                      
                                                                              
; unix                                                                        
;print,format='(" Compression % (output/input) = ",f5.1)', $                  
                                                                              
; no format control                                                           
print,' Compression % (output/input) = ', $                                   
    100.*nout/float(2*npts)                                                   
;                                                                             
;    Calculate and store the large differences as 16-bit numbers, if any      
;                                                                             
if nbad gt 0 then begin                                                       
                                                                              
;  MUST specify LONG word on the SUN!!!                                       
;   badx         = bad + indgen( nbad ) * 2 + 1    ; subscripts of big diffs  
   badx         = bad + lindgen( nbad ) * 2 + 1    ; subscripts of big diffs  
   badx1        = badx + 1  &  badx2 = badx + 2    ; 3 bytes per big diff     
   out( badx )  = 255b                             ; 255 is big diff flag     
   index        = [ badx1, badx2]                  ; index to new pixel val   
   index        = index( sort( index ))          ; sort into increasing order 
                                                                              
;  NOTE: no byte swapping required on SUN...only on VAX                       
;   temp         = image( bad )             ; prepare big pixels for swap     
;    dif(bad)     = image( bad )            ; prepare big pixels for swap     
;   swap, temp                              ; do the swap                     
;   dif( bad )   = temp                     ; put 'em into DIF                
;   out( index ) = byte( dif(bad), 0, 2*nbad)    ; write big pixels as 2 bytes
                                                                              
   out( index ) = byte( image(bad), 0, 2*nbad)   ; write big pixels as 2 bytes
endif                                                                         
;                                                                             
;    Calculate and store the small differences as 8-bit numbers               
;                                                                             
                                                                              
;  MUST specify LONG word on the SUN!!!                                       
goodout = lindgen( nout )              ; pointers to 8-bit output             
if nbad gt 0 then begin                ; get rid of big diff indices          
   remove, [ 0, 1, badx, badx1, badx2], goodout                               
endif else begin                                                              
   remove, [ 0, 1], goodout                                                   
endelse                                                                       
                                                                              
;  MUST specify LONG word on the SUN!!!                                       
good = lindgen( npts )             ; pointer array to 8-bit input             
remove, [ 0, bad], good            ; get rid of big diff indices              
;                                                                             
;    Swap bytes in the first pixel                                            
;                                                                             
;temp   = image(0)                                                            
;swap, temp                                                                   
;out(0) = byte( temp, 0, 2)                                                   
out(0) = byte( image, 0, 2)                                                   
;                                                                             
;    Put the 8-bit values into OUT                                            
;                                                                             
print,'convert and store unsigned bytes'                                      
out( goodout ) = dif( good ) + 127    ; Convert and store unsigned bytes      
;                                                                             
;    Pass OUT back to main program                                            
;                                                                             
print,'returning from prevpix'                                                
return, out                                                                   
end                                                                           
