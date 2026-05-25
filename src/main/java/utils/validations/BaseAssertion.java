package utils.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.WaitManager;
import utils.actions.ElementActions;

public abstract class BaseAssertion {
    protected  WebDriver driver;
    protected  WaitManager wait;
    protected  ElementActions elementActions;
    public BaseAssertion() {

    }

    public BaseAssertion(WebDriver driver, WaitManager wait) {
        this.driver = driver;
        this.wait = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract BaseAssertion assertTrue(boolean condition, String message);

    protected abstract BaseAssertion assertFalse(boolean condition, String message);

    protected abstract BaseAssertion assertEquals(String actual, String expected, String message);

    public void isElementVisible(By locator) {
        boolean visible;

        try {
            wait.waitForVisibility(locator);
            visible = true;
        } catch (TimeoutException e) {
            visible = false;
        }

        assertTrue(visible, "Element visibility check failed: " + locator);
    }

    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }


    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }

    public void assertElementText(By locator, String expectedText) {
        String actualText = wait.waitForVisibility(locator).getText();
        assertEquals(actualText, expectedText,
                "Text does not match for element: " + locator);
    }

    public void assertElementContainsText(By locator, String expectedPart) {
        String actualText = wait.waitForVisibility(locator).getText();
        assertTrue(actualText != null && actualText.contains(expectedPart),
                "Element text does not contain '" + expectedPart + "': " + locator);
    }

    public void assertElementAttribute(By locator, String attribute, String expectedValue) {
        String actualValue = wait.waitForVisibility(locator).getAttribute(attribute);
        assertEquals(actualValue, expectedValue,
                "Attribute '" + attribute + "' does not match for element: " + locator);
    }

    public void assertAlertText(String expectedText) {
        String actualText = wait.waitForAlert().getText();
        assertEquals(actualText, expectedText,
                "Alert text does not match. Expected: " + expectedText + ", Actual: " + actualText);
    }


}





