package gov.nasa.pds.entities;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ClassXmlAdapter extends XmlAdapter<String, Class<?>> {
    private static final String PACKAGE_PREFIX = "gov.nasa.pds.entities.";

    @Override
    public Class<?> unmarshal(String classShortName) throws Exception {
        String className = PACKAGE_PREFIX + classShortName;
        return Class.forName(className);
    }

    @Override
    public String marshal(Class<?> entityClass) throws Exception {
        return entityClass.getSimpleName();
    }
}
