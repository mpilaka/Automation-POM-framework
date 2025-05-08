
/**
 * File: src/main/java/com/cms/pageobjects/BasePage.java
 * Description: Base class for all Page Objects
 */
package com.cms.pageobjects;

import com.cms.utils.DriverManager;
import com.cms.utils.LoggerUtil;
import com.cms.utils.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected SeleniumActions actions;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.actions = new SeleniumActions();
        PageFactory.initElements(driver, this);
        verifyPage();
    }

    // Verify that the page is loaded correctly
    protected abstract void verifyPage();

    // Navigate to page
    public void navigateTo(String url) {
        LoggerUtil.getLogger().info("Navigating to URL: " + url);
        actions.navigateTo(url);
    }

    // Wait for page to load completely
    public void waitForPageToLoad(By elementToWaitFor) {
        LoggerUtil.getLogger().info("Waiting for page to load: " + this.getClass().getSimpleName());
        actions.isElementDisplayed(elementToWaitFor); // This will wait until the element is displayed
    }

    // Get page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Get current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Take screenshot
    public String takeScreenshot(String fileName) {
        return actions.takeScreenshot(fileName);
    }
}
