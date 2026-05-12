# OK Test Automation Framework

Java Selenium WebDriver automation framework built with Maven, TestNG, the Page Object Model, and Allure reporting.

## Overview

This project contains UI automation coverage for:

- [The Internet](https://the-internet.herokuapp.com)
- [Automation Exercise](https://automationexercise.com)

The framework includes reusable browser setup, page objects, custom TestNG listeners, validation helpers, screenshot capture, screen recording support, logging, JSON/property data readers, and Allure report generation.

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Allure TestNG
- Log4j2
- Rest Assured
- JSONPath / json-simple

## Project Structure

```text
src/main/java/driver                  Browser driver factory and options
src/main/java/pages                   Page objects and page components
src/main/java/utils                   Framework utilities and helpers
src/main/java/customlisteners         TestNG execution listeners
src/main/resources                    Framework configuration files
src/test/java                         Test suites
src/test/resources/test-data          Test data files
Flows                                 Framework flow diagrams
```

## Configuration

Browser and execution settings are controlled from:

```text
src/main/resources/webApp.properties
```

Supported browser values:

```properties
browserType=chrome
browserType=edge
```

Supported execution values:

```properties
executionType=local
executionType=localHeadless
executionType=remote
```

Other framework behavior is configured through the property files in `src/main/resources`, including wait settings, reporting, video recording, environment data, and logging.

## Prerequisites

- Java 17 or later
- Maven
- Chrome or Edge browser installed
- Allure command line available if you want to generate/open Allure reports locally

## Run Tests

Run the full TestNG suite:

```bash
mvn clean test
```

Run a specific test class:

```bash
mvn -Dtest=LoginTests test
```

## Reports And Outputs

Test execution output is generated under:

```text
test-outputs/
allure-results/
allure-report/
```

These folders are ignored by git because they are generated artifacts.

## Notes

- The framework uses `DriverFactory` with `ThreadLocal<WebDriver>` to isolate browser sessions.
- `TestNGListeners` handles execution setup, screenshots, logs, recordings, and Allure report generation.
- Page objects are split by application under `pages/internet` and `pages/com/automationexercices`.
