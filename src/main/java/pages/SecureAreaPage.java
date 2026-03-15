package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage {

    //****************************    Constructors    ****************************//
    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ******************************//
    private By welcomeMessage = By.id("flash");
    //**************************************************************************//


    //*************************     Methods    **********************************//
    public String getWelcomeMessage()
    {
        return driver.findElement(welcomeMessage).getText();
    }
    //***************************************************************************//

}


