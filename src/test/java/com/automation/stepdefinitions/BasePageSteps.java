package com.automation.steps;

import com.automation.pages.BasePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePageSteps {
    BasePage basePage = new BasePage() {
        @Override
        protected void verifyPage() {
            // No specific verification for the base page
        }
    };

    @Given("I open the browser")
    public void iOpenTheBrowser() {
        Assert.assertNotNull(basePage.driver, "WebDriver is not initialized.");
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        basePage.navigateTo(url);
    }

    @Then("I should see the page title as {string}")
    public void iShouldSeeThePageTitleAs(String expectedTitle) {
        Assert.assertEquals(basePage.getPageTitle(), expectedTitle, "Page title does not match!");
    }

    @When("I fetch the current URL")
    public void iFetchTheCurrentURL() {
        String currentUrl = basePage.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL is null.");
    }

    @Then("the URL should be {string}")
    public void theURLShouldBe(String expectedUrl) {
        Assert.assertEquals(basePage.getCurrentUrl(), expectedUrl, "Current URL does not match!");
    }

    @When("I wait for the element with locator {string}")
    public void iWaitForTheElement(String locator) {
        By elementLocator = By.xpath(locator);
        basePage.waitForPageToLoad(elementLocator);
    }

    @Then("the element should be displayed")
    public void theElementShouldBeDisplayed() {
        // Verify the element is displayed
    }

    @When("I take a screenshot with filename {string}")
    public void iTakeAScreenshot(String fileName) {
        String screenshotPath = basePage.takeScreenshot(fileName);
        Assert.assertNotNull(screenshotPath, "Screenshot was not saved.");
    }

    @Then("the screenshot should be saved successfully")
    public void theScreenshotShouldBeSavedSuccessfully() {
        // Additional validation can be added to check if the file exists
    }
}
