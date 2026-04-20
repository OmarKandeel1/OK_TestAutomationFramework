package pages.internet;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.internet.base.BasePage;

public class BasicAuthPage extends BasePage {
    //****************************    Constructors    ****************************//
    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By congratsLocator = By.cssSelector("#content p");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Validate that the user is logged in with {username} and {password}")
    public void loginIn(String username , String password)
    {
        // Build the authenticated URL
        String authUrl = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
        windowManager.goToURL(authUrl);
    }
    public String getText()
    {
        return elementActions.safeGetText(congratsLocator);
    }


    //**************************************************************************//
}
