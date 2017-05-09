
package gov.nasa.pds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for namedEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="namedEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pds.nasa.gov/}identifiableEntity">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "namedEntity", propOrder = {
    "name"
})
@XmlSeeAlso({
    Property.class,
    MetadataObject.class,
    DataSet.class,
    TargetType.class,
    Instrument.class,
    InstrumentHost.class,
    Mission.class,
    Target.class,
    Volume.class
})
public abstract class NamedEntity
    extends IdentifiableEntity
{

    protected String name;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
