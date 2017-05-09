package gov.nasa.pds.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.core.io.Resource;

public class ResourceMapFactoryBean extends MapFactoryBean {
    public ResourceMapFactoryBean() {
        super();
    }

    public void setResource(Resource resource) throws IOException {
        Scanner scanner = new Scanner(resource.getInputStream());
        try {
            Map<String, String> source = new HashMap<String, String>();
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    String value = scanner.nextLine();
                    source.put(key, value);
                }
            }
            super.setSourceMap(source);
        } finally {
            scanner.close();
        }
    }
}
