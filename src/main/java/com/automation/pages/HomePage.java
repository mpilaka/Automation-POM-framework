
/**
 * File: src/main/java/com/cms/pageobjects/HomePage.java
 * Description: Page Object for Homepage
 */
package com.cms.pageobjects;

import com.cms.utils.ConfigManager;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    // Page elements
    private final By headerLogo = By.id("cms-logo");
    private final By welcomeMessage = By.xpath("//h1[contains(text(),'Welcome to Medicare & Medicaid Eligibility Portal')]");
    private final By eligibilityCheckButton = By.id("check-eligibility");
    private final By enrollmentButton = By.id("enrollment");
    private final By loginButton = By.id("login");
    private final By registerButton = By.id("register");
    private final By searchField = By.id("search-field");
    private final By searchButton = By.id("search-button");
    private final By helpLink = By.id("help-link");

    public HomePage() {
        super();
    }

    @Override
    protected void verifyPage() {
        waitForPageToLoad(headerLogo);
        waitForPageToLoad(welcomeMessage);
    }

    public void navigateToHomePage() {
        navigateTo(ConfigManager.getInstance().getBaseUrl());
    }

    public EligibilityPage clickCheckEligibility() {
        actions.click(eligibilityCheckButton);
        return new EligibilityPage();
    }

    public EnrollmentPage clickEnrollment() {
        actions.click(enrollmentButton);
        return new EnrollmentPage();
    }

    public LoginPage clickLogin() {
        actions.click(loginButton);
        return new LoginPage
          }
