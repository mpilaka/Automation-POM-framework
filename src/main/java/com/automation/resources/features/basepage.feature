Feature: BasePage Functionality
  As a test automation developer,
  I want to verify the core functionalities of the BasePage
  So that all derived page objects behave correctly.

  Scenario: Navigate to a URL
    Given I open the browser
    When I navigate to "https://seleniumexamplepage.com"
    Then I should see the page title as "Example Domain"

  Scenario: Verify current URL
    Given I have navigated to "https://seleniumexamplepage.com"
    When I fetch the current URL
    Then the URL should be "https://seleniumexamplepage.com"

  Scenario: Verify page title
    Given I have navigated to a webpage
    When I fetch the page title
    Then the title should match "Expected Page Title"

  Scenario: Wait for an element to load
    Given I am on the "HomePage"
    When I wait for the element with locator "//*[@id='elementId']"
    Then the element should be displayed

  Scenario: Take a screenshot
    Given I have navigated to "https://example.com"
    When I take a screenshot with filename "screenshot.png"
    Then the screenshot should be saved successfully
