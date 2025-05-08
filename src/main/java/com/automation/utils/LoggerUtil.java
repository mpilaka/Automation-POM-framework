
/**
 * File: src/main/java/com/cms/utils/LoggerUtil.java
 * Description: Utility for logging
 */
package com.cms.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    private LoggerUtil() {
        // Private constructor to prevent instantiation
    }

    public static Logger getLogger() {
        return logger;
    }
}

/**
 * File: src/main/java/com/cms/utils/DriverManager.java
 * Description: Browser driver management utility
 */
package com.cms.utils;

import com.cms.constants.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initializeDriver();
        }
        return driverThreadLocal.get();
    }

    public static void initializeDriver() {
        String browserName = System.getProperty("browser", ConfigManager.getInstance().getProperty("default.browser", "chrome"));
        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());

        WebDriver driver;
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                driver = new SafariDriver(safariOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }

        // Set timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getInstance().getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getInstance().getPageLoadTimeout()));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(ConfigManager.getInstance().getScriptTimeout()));
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
