package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.LocatorUtils;
import utils.dataReader.PropertyReader;

public class SignupLoginPage {
    private final GUIDriver gui;
    private final String signupLoginEndpoint = "/login";

    //****************************    Constructors    ****************************//
    public SignupLoginPage(GUIDriver driver) {
        this.gui = driver;
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private final By signupNameInput = LocatorUtils.byQa("signup-name");
    private final By signupEmailInput = LocatorUtils.byQa("signup-email");
    private final By signupButton = LocatorUtils.byQa("signup-button");
    private final By loginEmailInput = LocatorUtils.byQa("login-email");
    private final By loginPasswordInput = LocatorUtils.byQa("login-password");
    private final By loginButton = LocatorUtils.byQa("login-button");
    private final By signupLabel = By.cssSelector(".signup-form>h2");
    private final By loginError = By.cssSelector(".login-form p");
    private final By registerError = By.cssSelector(".signup-form p");
    //**************************************************************************//


    //*************************   Methods    ************************************//
    @Step("Navigate to Signup/Login page")
    public SignupLoginPage navigate() {
        gui.browser().goToURL(PropertyReader.getProperty("baseUrlWeb") + signupLoginEndpoint);
        return this;
    }

    @Step("Enter signup name: {name}")
    public SignupLoginPage enterSignupName(String name) {
        gui.element().safeSendKeys(signupNameInput, name);
        return this;
    }

    @Step("Enter signup email: {email}")
    public SignupLoginPage enterSignupEmail(String email) {
        gui.element().safeSendKeys(signupEmailInput, email);
        return this;
    }

    @Step("Click on signup button")
    public SignUpPage clickSignupButton() {
        gui.element().safeClick(signupButton);
        return new SignUpPage(gui);
    }

    @Step("Enter login email: {email}")
    public SignupLoginPage enterLoginEmail(String email) {
        gui.element().safeSendKeys(loginEmailInput, email);
        return this;
    }

    @Step("Enter login password: {password}")
    public SignupLoginPage enterLoginPassword(String password) {
        gui.element().safeSendKeys(loginPasswordInput, password);
        return this;
    }
    public SignupLoginPage clickLoginButton() {
        gui.element().safeClick(loginButton);
        return this;
    }


    //***************************************************************************//

    //*************************   Validations    ********************************//
    @Step("Verify new User signup is visible")
    public SignupLoginPage verifySignupLabelVisible() {
        gui.verification().isElementVisible(signupLabel);
        return this;
    }

    @Step("Verify login error message: {expectedMessage}")
    public SignupLoginPage verifyLoginErrorMessage(String expectedMessage) {
        String actualMessage = gui.element().safeGetText(loginError);
        gui.verification().assertEquals(actualMessage, expectedMessage, "Login error message does not match expected.");
        return this;
    }

    @Step("Verify registration error message: {expectedMessage}")
    public SignupLoginPage verifyRegistrationErrorMessage(String expectedMessage) {
        String actualMessage = gui.element().safeGetText(registerError);
        gui.verification().assertEquals(actualMessage, expectedMessage, "Registration error message does not match expected.");
        return this;
    }


    //**************************************************************************//


}
