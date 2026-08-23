package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TestConfig {

    private static final Properties properties = new Properties();

    static {
        try (
                InputStream input = TestConfig.class
                        .getClassLoader()
                        .getResourceAsStream("config.properties"); InputStreamReader reader
                = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                    properties.load(reader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}