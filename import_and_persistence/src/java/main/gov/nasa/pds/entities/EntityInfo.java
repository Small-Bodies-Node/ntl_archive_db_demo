package gov.nasa.pds.entities;


public class EntityInfo {
    long id;
    String name;

    public EntityInfo() {
        // Empty
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        EntityInfo other = (EntityInfo) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return (int)id;
    }

    public EntityInfo(NamedEntity namedEntity) {
        this.id = namedEntity.getId();
        this.name = namedEntity.getName();
    }

    // id property
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // name property
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
