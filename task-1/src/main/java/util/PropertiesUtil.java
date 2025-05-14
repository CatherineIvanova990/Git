package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties properties = new Properties();

    private PropertiesUtil() {
    }

    static {
        loadProperties();
    }

    public static String getProperties(String key){
         return properties.getProperty(key);
    }

    private static void loadProperties() {
        try (var resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")){
            properties.load(resourceAsStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
