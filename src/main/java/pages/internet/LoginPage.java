package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

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
        actionsbot.safeSendKeys(usernameField,username);
    }

    public void setPassword(String password) {
        actionsbot.safeSendKeys(passwordField,password);
    }

    public SecureAreaPage clickLoginButton() {
        actionsbot.safeClick(loginButton);
        return new SecureAreaPage(driver);
    }
    //**************************************************************************//

}
