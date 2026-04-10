package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionBot {
    private WebDriver driver;
    private WaitManager wait;


    public ActionBot(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitManager(driver);
    }


    public void safeClick(By locator_) {
        wait.waitForClickable(locator_).click();
    }

    public void safeSendKeys(By locator, String text) {
        WebElement element = wait.waitForClickable(locator);
        element.clear();    //To avoid appending to old text
        element.sendKeys(text);
    }

    public String safeGetText(By locator) {
        return wait.waitForVisibility(locator).getText();
    }

}
