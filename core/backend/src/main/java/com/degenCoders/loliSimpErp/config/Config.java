package com.degenCoders.loliSimpErp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        try (InputStream fis = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (fis != null) {
                properties.load(fis);
            } else {
                System.err.println("config.properties file not found in classpath!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
