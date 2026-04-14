package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class ContextMenuPage extends BasePage {

    //****************************    Constructors    ****************************//
    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By boxLocator = By.id("hot-spot");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void rightClickOnBox() {
        action.contextClick(driver.findElement(boxLocator)).perform();
    }

    public void closeJavaAlert() {
        driver.switchTo().alert().accept();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    //**************************************************************************//
}
