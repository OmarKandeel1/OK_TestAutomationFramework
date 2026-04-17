package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

import java.time.Duration;
import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class WaitManager {
    private final WebDriver driver;
    private WebDriverWait wait;


    private static final int DEFAULT_TIMEOUT_SECONDS = Integer.parseInt(PropertyReader.getProperty("DEFAULT_TIMEOUT_SECONDS").trim());
    private static final int DEFAULT_POLLING_MS = Integer.parseInt(PropertyReader.getProperty("DEFAULT_POLLING_MS").trim());

    public WaitManager(WebDriver driver_) {
        this.driver = driver_;
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }


    private ArrayList<Class<? extends Exception>> getExceptions() {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>(); // ? for template

        exceptions.add(NoSuchElementException.class);
        exceptions.add(StaleElementReferenceException.class);

        return exceptions;
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofMillis(DEFAULT_POLLING_MS))
                .ignoreAll(getExceptions());
    }


    public WebElement waitForVisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for visibility of element: " + locator);
            throw e;
        }
    }

    public WebElement waitForClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for element to be clickable: " + locator);
            throw e;
        }
    }

    public boolean waitForElementTextToContain(By locator, String text) {
        try {
            wait.until(textToBePresentInElementLocated(locator, text));
            return true;
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for text '" + text + "' in element: " + locator);
            return false;
        }
    }

    public boolean waitForElementTextNotToContain(By locator, String text) {
        try {
            wait.until(ExpectedConditions.not(textToBePresentInElementLocated(locator, text)));
            return true;
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for text '" + text + "' to disappear from element: " + locator);
            return false;
        }
    }

    public void waitForInvisibility(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for element to become invisible: " + locator);
            throw e;
        }
    }

    //Custom Condition //Note i found there is exact function but i like my implementation :D "wait.until(ExpectedConditions.attributeContains(locator, attributeName, expectedSubstring));"
    public boolean waitForAttribute(By locator, String attributeName, String expectedSubstring) {
        try {
            wait.until(driver -> {
                try {
                    String actual = driver.findElement(locator).getAttribute(attributeName);
                    return actual != null && actual.contains(expectedSubstring);
                } catch (NoSuchElementException |
                         StaleElementReferenceException e) { //catch exception if findElement throw excpetion
                    return false;   // keep waiting
                }
            });
            return true;
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for attribute '" + attributeName + "' to contain '" + expectedSubstring + "' in element: " + locator);
            return false;
        }
    }


    public Alert waitForAlert() {
        try {
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting for alert to be present");
            throw e;
        }
    }


    public void switchToFrame(By locator) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting to switch to frame by locator: " + locator);
            throw e;
        }
    }

    public void switchToFrame(int index) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting to switch to frame by index: " + index);
            throw e;
        }
    }

    public void switchToFrame(WebElement frameElement) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
        } catch (TimeoutException e) {
            LogsManager.error("Timeout waiting to switch to frame by WebElement");
            throw e;
        }
    }


}



