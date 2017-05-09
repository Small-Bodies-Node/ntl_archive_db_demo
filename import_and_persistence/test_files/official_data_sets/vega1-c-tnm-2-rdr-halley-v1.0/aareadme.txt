PDS_VERSION_ID = PDS3                                                         
                                                                              
RECORD_TYPE    = "STREAM"                                                     
                                                                              
OBJECT     = TEXT                                                             
  PUBLICATION_DATE = 2011-05-25                                               
  NOTE             = "AAREADME File for re-organized IHW data set"            
END_OBJECT = TEXT                                                             
                                                                              
END                                                                           
                                                                              
Re-organized International Halley Watch (IHW) Data Sets                       
=======================================================                       
The original IHW volume HAL_0025 and HAL_0026 contains data from              
several spacecraft that flew by comets Halley and Giacobini-Zinner,           
over 40 data sets in all on the 2 volumes.  The data were sorted              
into subdirectories by spacecraft and instrument, with shared                 
supporting directories.                                                       
                                                                              
The data were also archived under a fairly early form of the PDS              
Standards, and formatted to an equally early form of the FITS table           
standard, where appropriate, occasionally producing some arcane               
formats for the modern user - including PDS labels with deprecated            
terminology and FITS files that sometimmes have their header segments         
separated from their data segments.                                           
                                                                              
The reorganization effort sought to separate the data files constituting      
the unique data sets into a more contemporary, single-data-set-per-volume     
format for ease of access and distribution.  The data have been sorted        
into a subdirectory hierarchy based on observation date, unless the number    
of data files was small enough that this seemed more disruptive than          
helpful, in which case all the observations are located directly in the       
DATA/ subdirectory.                                                           
                                                                              
Although the original disks included executable files for some simple         
utilities, these have been omitted from the reorganized data.  No modern      
system would run the executables, and in most cases no source code was        
provided.  For those rare cases where source was provided, it has been        
relocated to a relevant support directory where its principal value is        
as documentation. Note that PDS does not and will not support this code.      
                                                                              
- A.C.Raugh, 2011-05-25                                                       
