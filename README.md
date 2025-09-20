# Selenium & Playwright Test Automation Framework

This project is a test automation framework supporting Playwright, built with Java and Maven. It provides a unified interface for running UI tests across multiple browsers

## Features

- Supports Microsoft Playwright
- ExtentReports integration for rich test reporting
- Screenshot capture utility for failed test cases
- Configurable via properties file
- Easy switching between browsers

## Prerequisites

- Java 18 or higher
- Maven 3.x
- Chrome, Safari & Firefox browsers installed

## Getting Started

1. **Clone the repository:**
2. **Configure the framework:**
    - Edit `src/test/resources/config.properties` to set your desired browser and framework:
      ```
      BROWSER=chrome
      ```
3. **Run tests: mvn test**

## Project Structure

- `framework/util/` - Utilities for Playwright & Test wrapper for managing driver/page lifecycle
- `framework/report/` - Reporting utilities (ExtentReports, ScreenshotUtil)
- `src/test/java/regression` - Test cases

## Reporting

Test execution reports are generated using ExtentReports and can be found in the `test-output/` directory after running tests.