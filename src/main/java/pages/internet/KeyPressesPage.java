package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.internet.base.BasePage;

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
