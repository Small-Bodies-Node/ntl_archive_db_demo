
package gov.nasa.pds.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.5.2
 * Thu Apr 26 23:51:09 EEST 2012
 * Generated source version: 2.5.2
 */

@XmlRootElement(name = "getDataSetRelatedEntititesResponse", namespace = "http://pds.nasa.gov/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDataSetRelatedEntititesResponse", namespace = "http://pds.nasa.gov/")

public class GetDataSetRelatedEntititesResponse {

    @XmlElement(name = "return")
    private java.util.List<gov.nasa.pds.entities.EntityInfo> _return;

    public java.util.List<gov.nasa.pds.entities.EntityInfo> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<gov.nasa.pds.entities.EntityInfo> new_return)  {
        this._return = new_return;
    }

}

