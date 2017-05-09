package gov.nasa.pds.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.core.io.Resource;

public class ResourceSetFactoryBean extends SetFactoryBean {
    public ResourceSetFactoryBean() {
        super();
    }

    public void setResource(Resource resource) throws IOException {
        Scanner scanner = new Scanner(resource.getInputStream());
        try {
            Set<String> source = new HashSet<String>();
            while (scanner.hasNextLine()) {
                source.add(scanner.nextLine());
            }
            super.setSourceSet(source);
        } finally {
            scanner.close();
        }
    }
}
