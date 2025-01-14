import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return -1;
    }

    public static long getLongProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Long.parseLong(value);
        }
        return -1L;
    }
}
