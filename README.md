# Selenium & Playwright Test Automation Framework

This project is a test automation framework supporting both Selenium and Playwright, built with Java and Maven. It provides a unified interface for running UI tests across multiple browsers and frameworks.

## Features

- Supports Selenium WebDriver and Microsoft Playwright
- ExtentReports integration for rich test reporting
- Screenshot capture utility for both Selenium and Playwright
- Configurable via properties file
- Easy switching between frameworks and browsers

## Prerequisites

- Java 18 or higher
- Maven 3.x
- Chrome & Firefox browsers installed

## Getting Started

1. **Clone the repository:**
2. **Configure the framework:**
    - Edit `src/test/resources/config.properties` to set your desired browser and framework:
      ```
      BROWSER=chrome
      testFramework=selenium
      ```
3. **Run tests: mvn test**

## Project Structure

- `framework/` - Core framework classes and utilities
- `framework/wrappers/` - Test wrapper for managing driver/page lifecycle
- `framework/util/` - Utilities for Selenium and Playwright
- `framework/report/` - Reporting utilities (ExtentReports, ScreenshotUtil)
- `src/test/java/` - Test cases

## Reporting

Test execution reports are generated using ExtentReports and can be found in the `test-output/` directory after running tests.