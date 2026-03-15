package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitManager;
import utils.WindowManager;

public class BasePage {
    protected WebDriver driver;
    protected WaitManager wait;
    protected WindowManager windowManager;
    protected Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitManager(driver);
        windowManager = new WindowManager(driver);
        action  = new Actions(driver);
    }

    public void  safeClick(By locator_) {
       wait.waitForClickable(locator_).click();
    }

    protected void safeSendKeys(By locator, String text) {
        WebElement element = wait.waitForClickable(locator);
        element.clear();    //To avoid appending to old text
        element.sendKeys(text);
    }

    protected String safeGetText(By locator) {
        return wait.waitForVisibility(locator).getText();
    }





}
