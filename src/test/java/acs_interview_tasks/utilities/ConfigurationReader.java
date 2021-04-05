package acs_interview_tasks.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final Properties properties = new Properties();

    static {
        {
            try {
                FileInputStream file = new FileInputStream("configuration.properties");
                properties.load(file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load properties file! ");
            }
        }

    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}

