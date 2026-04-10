package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewWindowPage extends BasePage{
    //****************************    Constructors    ****************************//
    public NewWindowPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By textLocator = By.tagName("h3");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public String getText(){

        return actionsbot.safeGetText(textLocator);
    }
    //**************************************************************************//
}
