package utils.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utils.WaitManager;
import utils.logs.LogsManager;

import java.io.File;

public class ElementActions {
    private  final WebDriver driver;
    private final WaitManager wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitManager(driver);
    }

    //Find an Element with no wait
    public  WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        LogsManager.info("Found element: " + locator);
        return element;
    }


    public ElementActions safeClick(By locator) {
        wait.waitForClickable(locator).click();
        LogsManager.info("Safe clicked element: " + locator);
        return this;
    }

    public ElementActions safeSendKeys(By locator, String text) {
        WebElement element = wait.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
        LogsManager.info("Sent text safely to element: " + locator + " | Text: " + text);
        return this;
    }

    public String safeGetText(By locator) {
        String text = wait.waitForVisibility(locator).getText();
        LogsManager.info("Got text safely from element: " + locator + " | Text: " + text);
        return text;
    }

    public String getText(By locator) {
        String text = findElement(locator).getText();
        LogsManager.info("Got text not safely from element: " + locator + " | Text: " + text);
        return text;
    }



    //This function will scroll to an element using JS
    public ElementActions scrollToElementJS(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("""
                arguments[0].scrollIntoView({behavior:"auto",block:"center",inline:"center"});
                """, element);
        LogsManager.info("Scrolled to element: " + locator);
        return this;
    }

    //Upload File only work with <input type="file">
    public ElementActions uploadFile(By locator_, String filePath) {
        String fileAbsolute = System.getProperty("user.dir") + File.separator + filePath;

        File file = new File(fileAbsolute);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + fileAbsolute);
        }

        WebElement element = wait.waitForVisibility(locator_);
        element.sendKeys(fileAbsolute);
        LogsManager.info("Uploaded file: " + fileAbsolute + " using element: " + locator_);
        return this;
    }

    //Select from DropDown
    private Select findDropDownElement(By locator) {
        WebElement element = wait.waitForVisibility(locator);
        return new Select(element);
    }

    public ElementActions selectFromDropDown(By locator_ , String value)
    {
        Select element = findDropDownElement(locator_);
        element.selectByValue(value);
        LogsManager.info("Selected value: " + value + " from dropdown: " + locator_);
        return this;
    }

    public String getSelectedDropDownText(By locator) {
        Select dropdown = findDropDownElement(locator);
        String selectedText = dropdown.getFirstSelectedOption().getText();
        LogsManager.info("Selected dropdown text is: " + selectedText + " from dropdown: " + locator);
        return selectedText;
    }

    public ElementActions hover(By locator)
    {
        scrollToElementJS(locator);
        new Actions(driver).moveToElement(findElement(locator)).perform();
        LogsManager.info("Hovered over element: " + locator);
        return this;
    }



}
