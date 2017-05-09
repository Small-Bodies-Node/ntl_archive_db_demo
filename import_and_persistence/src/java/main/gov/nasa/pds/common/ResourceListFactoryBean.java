package gov.nasa.pds.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.core.io.Resource;

public class ResourceListFactoryBean extends ListFactoryBean {
    public ResourceListFactoryBean() {
        super();
    }

    public void setResource(Resource resource) throws IOException {
        Scanner scanner = new Scanner(resource.getInputStream());
        try {
            List<String> source = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                source.add(scanner.nextLine());
            }
            super.setSourceList(source);
        } finally {
            scanner.close();
        }
    }
}
