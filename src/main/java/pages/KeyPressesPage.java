package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class KeyPressesPage extends BasePage {

    //****************************    Constructors    ****************************//
    public KeyPressesPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By inputField = By.id("target");
    private By resultMessage = By.id("result");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void setText(String key_)
    {
        driver.findElement(inputField).sendKeys(key_);
    }

    public String getEnteredText()
    {
        return  driver.findElement(resultMessage).getText();

    }
    //**************************************************************************//


}
