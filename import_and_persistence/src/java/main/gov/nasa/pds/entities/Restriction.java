package gov.nasa.pds.entities;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Restriction {
    private long restrictionEntityId;
    private Class<?> restrictionEntityClass;

    public Restriction() {
        // Empty
    }

    public Restriction(long restrictionEntityId, Class<?> restrictionEntityClass) {
        this.restrictionEntityId = restrictionEntityId;
        this.restrictionEntityClass = restrictionEntityClass;
    }

    // restrictionEntityId property
    public long getRestrictionEntityId() {
        return restrictionEntityId;
    }
    public void setRestrictionEntityId(long restrictiveEntityId) {
        this.restrictionEntityId = restrictiveEntityId;
    }

    // restrictiveEntityClass property
    @XmlJavaTypeAdapter(ClassXmlAdapter.class)
    public Class<?> getRestrictionEntityClass() {
        return restrictionEntityClass;
    }
    public void setRestrictionEntityClass(Class<?> restrictiveEntityClass) {
        this.restrictionEntityClass = restrictiveEntityClass;
    }
}
