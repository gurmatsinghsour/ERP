package com.degenCoders.loliSimpErp.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class Config {
    private static final Map<String, Object> config;

    static {
        try {
            InputStream input = Config.class.getClassLoader().getResourceAsStream("config.yml");
            if (input == null) {
                throw new RuntimeException("config.yml file not found in classpath!");
            }
            Yaml yaml = new Yaml();
            config = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.yml: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static String get(String key) {
        String[] parts = key.split("\\.");
        Object value = config;
        for (String part : parts) {
            if (value instanceof Map) {
                value = ((Map<String, Object>) value).get(part);
            } else {
                return null;
            }
        }
        return value != null ? value.toString() : null;
    }
}
