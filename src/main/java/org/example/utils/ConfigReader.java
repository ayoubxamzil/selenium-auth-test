package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();

    static {
        String path = System.getProperty("user.dir") + "/src/main/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("fichier introuvable");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}