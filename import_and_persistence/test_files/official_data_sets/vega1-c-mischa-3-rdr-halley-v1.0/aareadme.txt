PDS_VERSION_ID       = PDS3                                                   
RECORD_TYPE          = STREAM                                                 
OBJECT               = TEXT                                                   
  NOTE               = "AAREADME file"                                        
  PUBLICATION_DATE   = 2011-06-15                                             
END_OBJECT           = TEXT                                                   
END                                                                           
                                                                              
This data set, one of two originally archived on the physical volume          
HAL_1003, has been reformatted into a single-data-set-volume format as        
part of a general updating of legacy data to conform more closely to          
contemporary data presentation.  The original text of the HAL_1003            
AAREADME.TXT file is below.  Note that the data are now located under         
a directory called "DATA", and only data for the present data set are         
included.  Documents and other files relevant to both data sets are           
reproduced within each.                                                       
_____________________                                                         
                                                                              
A.C.Raugh, 2011-06-15                                                         
_____________________                                                         
                                                                              
***** File AAREADME.TXT                                                       
                                                                              
This data set collection contains MISCHA data for the International Halley    
Watch Archive from Vega 1 which has been collected, archived, processed,      
and prepared for this prototype CD-WO by teams associated with Space Research 
Institute (Austria) and the PDS Small Bodies and Plasma Planetary Interaction 
Nodes. This data set collection gathers additional material for the study of  
Comet Halley and the solar wind.                                              
                                                                              
Currently there are four groups of data:                                      
                                                                              
  Master files - flat file of magnetic field components (Bx,By,Bz,Bt,Bux)     
     with associated time in heliocentric solar ecliptic (H.SE) coord system  
  Survey files - flat file of magnetic field components (Bx,By,Bz,Bt,Bux)     
     with associated time in H.SE coord system                                
  fbt1se files - flat file of magnetic field components (Bx,By,Bz,Bt,Bux)     
     with associated time in H.SE coordinate system.   Data are collected     
     from 4 modes: DT, HS, T1, and T2- data averaged to 150 Sec sampling      
  fbt2se files - flat file as above except in only the DT, HS, T2 modes       
                                                                              
The data files were submitted in both the binary and ASCII form.   In the     
former case, the data were converted to ASCII tables were appropriate.        
The data were checked by IDL routine developed at the Small Bodies Node.      
Documents give explanatory text, calibrations (Postsrcipt or MS Word or ASCII 
with figures), and articles (digitized with permission by the Institute for   
Space Research).  All documents and labels contain an 80-byte fixed length    
record, with a carriage return (ASCII 13) and line feed (ASCII 10) as the     
last two bytes.   All the tabular data files as well as those files in the    
INDEX and DOCUMENT directories are formatted according to PDS standards.      
                                                                              
An outline of the CD-ROM is shown in the table below:                         
/CATALOG/*.CAT                    Mission, host, instrument, data set and ref 
/DOCUMENT/                        Explanatory documentation, errors, figures  
/DOCUMENT/PROCESS/DATACONV/       Data conversion explanation and examples    
/DOCUMENT/PROCESS/OFFSET/         Offset and spin information                 
/DOCUMENT/PROCESS/POSITION/       Position information for both phases        
/DOCUMENT/PLOT/                   Two sample plots of MISCHA data for Vega-1  
/INDEX/                           Index table for the data files              
/VEGA1/FLY_BY/                    Master and survey files based on SE coord   
/VEGA1/CRUISE/                    Master and Survey files based on SE coord   
                                                                              
                                                                              
Small Bodies Node, Planetary Data System                                      
Department of Astronomy, University of Maryland                               
College Park, MD 20742-2421                                                   
