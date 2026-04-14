package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

public class LargeAndDeepDomPage extends BasePage {
    //****************************    Constructors    ****************************//
    public LargeAndDeepDomPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By tableLocator = By.id("large-table");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void scrollToTable(){
        WebElement tableElement = driver.findElement(tableLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script,tableElement);

    }
    //**************************************************************************//
}
