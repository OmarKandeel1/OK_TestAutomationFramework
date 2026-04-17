package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    //****************************    Constructors    ****************************//
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("#login>button");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void setUserName(String username) {
        elementActions.safeSendKeys(usernameField,username);
    }

    public void setPassword(String password) {
        elementActions.safeSendKeys(passwordField,password);
    }

    public SecureAreaPage clickLoginButton() {
        elementActions.safeClick(loginButton);
        return new SecureAreaPage(driver);
    }
    //**************************************************************************//

}
