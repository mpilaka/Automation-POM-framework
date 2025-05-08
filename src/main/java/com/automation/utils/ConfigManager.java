/**
 * File: src/main/java/com/cms/utils/ConfigManager.java
 * Description: Utility to manage configuration properties
 */
package com.cms.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    private static ConfigManager instance;

    private ConfigManager() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            LoggerUtil.getLogger().error("Error loading config file: " + e.getMessage());
        }
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        String env = System.getProperty("environment", "qa").toLowerCase();
        return properties.getProperty(env + ".base.url");
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait", "10"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout", "30"));
    }

    public int getScriptTimeout() {
        return Integer.parseInt(properties.getProperty("script.timeout", "30"));
    }

    public boolean takeScreenshotOnFailure() {
        return Boolean.parseBoolean(properties.getProperty("take.screenshot.on.failure", "true"));
    }

    public String getScreenshotPath() {
        return properties.getProperty("screenshot.path", "target/screenshots/");
    }

    public String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }
}
