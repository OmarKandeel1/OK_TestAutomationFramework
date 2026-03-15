package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BasicAuthPage extends BasePage{
    //****************************    Constructors    ****************************//
    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By congratsLocator = By.cssSelector("#content p");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void loginIn(String username , String password)
    {
        // Build the authenticated URL
        String authUrl = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
        windowManager.goToURL(authUrl);
    }
    public String getText()
    {
        return safeGetText(congratsLocator);
    }


    //**************************************************************************//
}
