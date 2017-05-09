
package gov.nasa.pds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDataSetRelatedEntitites complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDataSetRelatedEntitites">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="relatedEntityType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDataSetRelatedEntitites", propOrder = {
    "dataSetId",
    "relatedEntityType"
})
public class GetDataSetRelatedEntitites {

    protected long dataSetId;
    @XmlElement(required = true)
    protected String relatedEntityType;

    /**
     * Gets the value of the dataSetId property.
     * 
     */
    public long getDataSetId() {
        return dataSetId;
    }

    /**
     * Sets the value of the dataSetId property.
     * 
     */
    public void setDataSetId(long value) {
        this.dataSetId = value;
    }

    /**
     * Gets the value of the relatedEntityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedEntityType() {
        return relatedEntityType;
    }

    /**
     * Sets the value of the relatedEntityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedEntityType(String value) {
        this.relatedEntityType = value;
    }

}
