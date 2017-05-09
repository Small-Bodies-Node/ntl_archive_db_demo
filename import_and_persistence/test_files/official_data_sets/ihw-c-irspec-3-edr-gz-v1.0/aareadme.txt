PDS_VERSION_ID = PDS3                                                         
                                                                              
RECORD_TYPE    = "STREAM"                                                     
                                                                              
OBJECT     = TEXT                                                             
  PUBLICATION_DATE = 2011-05-18                                               
  NOTE             = "AAREADME File for re-organized IHW data set"            
END_OBJECT = TEXT                                                             
                                                                              
END                                                                           
                                                                              
Re-organized International Halley Watch (IHW) Data Sets                       
=======================================================                       
The original IHW HAL_0024 disk contained ground-based observations from       
various networks ("nets" in IHW parlance) of comets Giacobini-Zinner and      
Crommelin - both used as test targets by the IHW observers.  These data       
on Comet Giacobini-Zinner, belonging to two dozen individual data sets,       
were intermixed on the original volume.  The data were sorted                 
chronologically by date of observation only, and were otherwise not           
separated by data set ID or type of observation.                              
                                                                              
The data were also archived under the PDS1 standards, which bear little       
resemblence to the PDS standards of today (currently PDS3, with PDS4 in       
development), and formatted to an equally early form of the FITS table        
standard, where appropriate, resulting in some particularly arcane            
formats for the modern user - including PDS labels with deprecated            
terminology and a preponderance of single-record FITS ASCII table             
files with header segments separated from data segments.                      
                                                                              
The reorganization effort sought to separate the data files constituting      
the unique data sets into a more contemporary, single-data-set-per-volume     
format for ease of access and distribution.  The data have been sorted        
into a subdirectory hierarchy based on observation date, unless the number    
of data files was small enough that this seemed more disruptive than          
helpful, in which case all the observations are located directly in the       
DATA/ subdirectory.                                                           
                                                                              
The data files and most supporting files are presented here in their          
original format, problematic though that might be.  Users interested in       
analyzing the data would probably do well to inquire of the PDS or the        
Small Bodies Node for version 2.0 of the observational data set.              
                                                                              
The additional ancillary directories, including documentation, ephemerides,   
software and such, are duplicated in their entirety in all the related        
separate data sets                                                            
                                                                              
- A.C.Raugh, 2011-05-18                                                       
