package cucumber.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFile {
    private static PropertiesFile instance = new PropertiesFile();
    private Map<String, String> loadedProperties = new HashMap<>();

    private PropertiesFile() {
    }

    public static PropertiesFile getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return loadedProperties.get(key);
    }

    public void readProperties() {
        Properties properties = new Properties();

        try (InputStream fis = new FileInputStream("src/main/resources/epam.properties")) {
            properties.load(fis);
            properties.forEach((key, value) -> loadedProperties.put(key.toString(), value.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}