pcdeclsp.exe:  pcdeclsp.obj header.obj xtension.obj do_ifile.obj \            
               do_dfile.obj do_ofile.obj putkey.obj do_data.obj \             
               decomp.obj strresiz.obj strreplc.obj                           
  tlink    /t e:\turbo\tc\lib\c0t @pcdeclsp.rsp, pcdeclsp, , \                
              e:\turbo\tc\lib\cs                                              
                                                                              
pcdeclsp.obj:  pcdeclsp.c                                                     
               tcc -c -mt -f- -v- pcdeclsp.c                                  
                                                                              
header.obj:    header.c                                                       
               tcc -c -mt -f- -v- header.c                                    
                                                                              
xtension.obj:  xtension.c                                                     
               tcc -c -mt -f- -v- xtension.c                                  
                                                                              
do_ifile.obj:  do_ifile.c                                                     
               tcc -c -mt -f- -v- do_ifile.c                                  
                                                                              
do_dfile.obj:  do_dfile.c                                                     
               tcc -c -mt -f- -v- do_dfile.c                                  
                                                                              
do_ofile.obj:  do_ofile.c                                                     
               tcc -c -mt -f- -v- do_ofile.c                                  
                                                                              
putkey.obj:    putkey.c                                                       
               tcc -c -mt -f- -v- putkey.c                                    
                                                                              
do_data.obj:   do_data.c                                                      
               tcc -c -mt -f- -v- do_data.c                                   
                                                                              
decomp.obj:    decomp.c                                                       
               tcc -c -mt -f- -v- decomp.c                                    
                                                                              
strresiz.obj:  strresiz.c                                                     
               tcc -c -mt -f- -v- strresiz.c                                  
                                                                              
strreplc.obj:  strreplc.c                                                     
               tcc -c -mt -f- -v- strreplc.c                                  
                                                                              
