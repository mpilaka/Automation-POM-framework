Automation-POM-Framework
This repository demonstrates a complete test automation framework for a CMS eligibility portal using Selenium, Cucumber, TestNG, and Maven. It is designed with CI/CD integration in mind to streamline automated testing workflows.

📋 Features
Page Object Model (POM) Design Pattern: Ensures maintainability and reusability of test scripts.
Cross-Browser Testing: Provides support for testing across multiple browsers.
Environment Configuration: Configured to work with multiple test environments seamlessly.
CI/CD Integration: Easily integrates with continuous integration/continuous deployment pipelines.

🛠️ Tech Stack
Programming Language: Java
Frameworks: Selenium, Cucumber, TestNG
Build Tool: Maven
Version Control: Git

📂 Repository Structure
Code
src/
├── main/
│   └── java/
│       └── com/
│           └── automation/
│               └── pages/      # Page object classes
│               └── utils/      # Utility classes
├── test/
│   └── java/
│       └── com/
│           └── automation/
│               └── steps/      # Step definitions for Cucumber
│               └── runner/     # TestNG/Cucumber runner classes
│   └── resources/
│       └── features/           # Cucumber feature files

Getting Started
Prerequisites
Java Development Kit (JDK): Version 8 or above.
Maven: Ensure Maven is installed and added to your system's PATH.
WebDriver: Download the appropriate WebDriver for your browser (e.g., ChromeDriver, GeckoDriver).


Installation
1. Clone this repository:
 git clone https://github.com/mpilaka/Automation-POM-framework.git
2. Navigate to the project directory:
 cd Automation-POM-framework
3. Install dependencies:
  mvn clean install

Usage
1. Update the configuration file for your test environment under the resources folder.
2. Run the tests using Maven:
    mvn test
3. Generate and view test reports:
   mvn site

License
 This project is licensed under the Apache License 2.0.
