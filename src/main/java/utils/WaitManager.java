package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class WaitManager {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;
    private static final int DEFAULT_POLLING_MS = 100;

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



    public WebElement waitForVisibility(By locator_) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator_));
    }

    public WebElement waitForClickable(By locator_) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator_));
    }

    public boolean waitForElementTextToContain(By locator_, String text_) {
        try {
            wait.until(textToBePresentInElementLocated(locator_, text_));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for text '" + text_ +
                    "' in element: " + locator_);
            return false;
        }
    }

    public boolean waitForElementTextNotToContain(By locator_, String text_) {
        try {
            wait.until(ExpectedConditions.not(textToBePresentInElementLocated(locator_, text_)));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for text '" + text_ +
                    "' in element: " + locator_);
            return false;
        }
    }

    public void waitForInvisibility(By locator_) {
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator_)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator_));
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
            return false;
        }
    }


}



