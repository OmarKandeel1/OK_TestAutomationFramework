package pages.com.automationexercices.components;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.com.automationexercices.pages.*;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

public class NavigationBarComponent {

    private final GUIDriver guiDriver;

    //****************************    Constructors    ****************************//
    public NavigationBarComponent(GUIDriver driver) {
        this.guiDriver = driver;
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private final By homeButton = By.xpath("//a[contains(.,\"Home\")]");
    private final By productButton = By.cssSelector("a[href='/products']");
    private final By cartButton = By.cssSelector("a[href='/view_cart']");
    private final By signUpLoginButton = By.cssSelector("a[href='/login']");
    private final By testCasesButton = By.cssSelector("a[href='/test_cases']");
    private final By apiTestingButton = By.cssSelector("a[href='/api_list']");
    private final By videoTutorialsButton = By.cssSelector("//a[contains(.,\"Video Tutorials\")]");
    private final By contactUsButton = By.cssSelector("a[href='/contact_us']");
    private final By logoutButton = By.cssSelector("a[href='/logout']");
    private final By homePageLabel = By.xpath("//h2[contains(.,\"Full-Fledged practice website for Automation Engineers\")]");
    private final By userLabel = By.tagName("b");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Navigate to Home Page")
    public NavigationBarComponent navigate() {
        guiDriver.browser().goToURL(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click Home Button")
    public NavigationBarComponent clickHomeButton() {
        guiDriver.element().safeClick(homeButton);
        return this;
    }

    @Step("Click Products Button")
    public ProductsPage clickProductsButton() {
        guiDriver.element().safeClick(productButton);
        return new ProductsPage(guiDriver);
    }

    @Step("Click Cart Button")
    public CartPage clickCartButton() {
        guiDriver.element().safeClick(cartButton);
        return new CartPage(guiDriver);
    }

    @Step("Click Sign Up/Login Button")
    public SignupLoginPage clickSignUpLoginButton() {
        guiDriver.element().safeClick(signUpLoginButton);
        return new SignupLoginPage(guiDriver);
    }

    @Step("Click Test Cases Button")
    public TestCasesPage clickTestCasesButton() {
        guiDriver.element().safeClick(testCasesButton);
        return new TestCasesPage(guiDriver);
    }

    @Step("Click API Testing Button")
    public ApiTestingPage clickApiTestingButton() {
        guiDriver.element().safeClick(apiTestingButton);
        return new ApiTestingPage(guiDriver);
    }

    @Step("Click Video Tutorials Button")
    public VideoTutorialsPage clickVideoTutorialsButton() {
        guiDriver.element().safeClick(videoTutorialsButton);
        return new VideoTutorialsPage(guiDriver);
    }

    @Step("Click Contact Us Button")
    public ContactUsPage clickContactUsButton() {
        guiDriver.element().safeClick(contactUsButton);
        return new ContactUsPage(guiDriver);
    }

    @Step("Click Logout Button")
    public LogoutPage clickLogoutButton() {
        guiDriver.element().safeClick(logoutButton);
        return new LogoutPage(guiDriver);
    }

    //**************************************************************************//

    //*************************   Validations    **********************************//
    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePageLabelVisible() {
        guiDriver.verification().isElementVisible(homePageLabel);
        return this;
    }

    @Step("Verify User Label")
    public NavigationBarComponent verifyUserLabelVisible(String expectedUser) {
        guiDriver.verification().assertElementText(userLabel, expectedUser);
        LogsManager.info("User label is visible and contains the expected text: " + expectedUser);
        return this;
    }

    //**************************************************************************//


}
