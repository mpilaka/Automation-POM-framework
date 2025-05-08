
/**
 * File: src/main/java/com/cms/utils/SeleniumActions.java
 * Description: Utility for common Selenium actions
 */
package com.cms.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class SeleniumActions {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 10;

    public SeleniumActions() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
    }

    public void navigateTo(String url) {
        try {
            LoggerUtil.getLogger().info("Navigating to URL: " + url);
            driver.get(url);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error navigating to URL: " + url + " - " + e.getMessage());
            throw e;
        }
    }

    public void click(By locator) {
        try {
            LoggerUtil.getLogger().info("Clicking element: " + locator);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error clicking element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void type(By locator, String text) {
        try {
            LoggerUtil.getLogger().info("Typing text: " + text + " into element: " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error typing text: " + text + " into element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public String getText(By locator) {
        try {
            LoggerUtil.getLogger().info("Getting text from element: " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error getting text from element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            LoggerUtil.getLogger().info("Checking if element is displayed: " + locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            LoggerUtil.getLogger().info("Element is not displayed: " + locator);
            return false;
        }
    }

    public void selectByVisibleText(By locator, String text) {
        try {
            LoggerUtil.getLogger().info("Selecting option: " + text + " from dropdown: " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            new Select(element).selectByVisibleText(text);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error selecting option: " + text + " from dropdown: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public void waitForElementToBeInvisible(By locator) {
        try {
            LoggerUtil.getLogger().info("Waiting for element to be invisible: " + locator);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error waiting for element to be invisible: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public String takeScreenshot(String fileName) {
        try {
            LoggerUtil.getLogger().info("Taking screenshot: " + fileName);
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

            String screenshotDir = ConfigManager.getInstance().getScreenshotPath();
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String screenshotPath = screenshotDir + fileName + ".png";
            Path path = Paths.get(screenshotPath);
            Files.write(path, screenshotBytes);

            return screenshotPath;
        } catch (IOException e) {
            LoggerUtil.getLogger().error("Error taking screenshot: " + e.getMessage());
            return null;
        }
    }

    public void scrollToElement(By locator) {
        try {
            LoggerUtil.getLogger().info("Scrolling to element: " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            // Add a small pause to allow the page to settle after scrolling
            Thread.sleep(500);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error scrolling to element: " + locator + " - " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
