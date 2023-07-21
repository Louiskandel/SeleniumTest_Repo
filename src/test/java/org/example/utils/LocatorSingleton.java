package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocatorSingleton {

    public static Properties readPropertiesFile(String path)  {
        Properties properties = null;
        try (FileInputStream fis = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fis);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return properties;
    }
}