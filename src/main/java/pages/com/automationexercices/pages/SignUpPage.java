package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.com.automationexercices.components.NavigationBarComponent;
import utils.LocatorUtils;

public class SignUpPage {


    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public SignUpPage(GUIDriver driver) {
        this.gui = driver;
    }
    //**************************************************************************//


    //****************************    Locators    ******************************//
    private final By nameLocator = By.id("name");
    private final By emailLocator = By.id("email");
    private final By passwordLocator = By.id("password");
    private final By dateDayLocator = By.id("days");
    ;
    private final By dateMonthLocator = By.id("months");
    private final By dateYearLocator = By.id("years");
    private final By firstNameLocator = By.id("first_name");
    private final By lastNameLocator = By.id("last_name");
    private final By companyLocator = By.id("company");
    private final By addressLocator = By.id("address1");
    private final By countryLocator = By.id("country");
    private final By stateLocator = By.id("state");
    private final By cityLocator = By.id("city");
    private final By zipCodeLocator = By.id("zipcode");
    private final By mobileNumberLocator = By.id("mobile_number");
    private final By createAccountButtonLocator = LocatorUtils.byQa("create-account");
    private final By enterAccInfoHeaderLocator = By.cssSelector(".login-form>h2 b");
    private final By accountCreatedLabelLocator = By.tagName("b");
    private final By continueButtonLocator = LocatorUtils.byQa("continue-button");


    //**************************************************************************//


    //*************************   Methods    ************************************//
    @Step("Choose title: {title}") //Mr - Mrs
    private SignUpPage chooseTitle(String title) {
        By titleLocator = By.xpath("//input[@value='" + title + "']");
        gui.element().safeClick(titleLocator);
        return this;
    }

    @Step("Fill the Registration form with: title={title}, name={name}, email={email}, password={password}, dob={day}-{month}-{year}, firstName={firstName}, lastName={lastName}, address={address}, country={country}, state={state}, city={city}, zip={zipCode}, mobile={mobileNumber}")
    public SignUpPage fillRegisterationForm(String title,
                                            String password,
                                            String day,
                                            String month,
                                            String year,
                                            String firstName,
                                            String lastName,
                                            String company,
                                            String address,
                                            String country,
                                            String state,
                                            String city,
                                            String zipCode, String mobileNumber) {
        chooseTitle(title);
        gui.element().safeSendKeys(passwordLocator, password);
        gui.element().selectFromDropDown(dateDayLocator, day);
        gui.element().selectFromDropDown(dateMonthLocator, month);
        gui.element().selectFromDropDown(dateYearLocator, year);
        gui.element().safeSendKeys(firstNameLocator, firstName);
        gui.element().safeSendKeys(lastNameLocator, lastName);
        gui.element().safeSendKeys(companyLocator, company);
        gui.element().safeSendKeys(addressLocator, address);
        gui.element().selectFromDropDown(countryLocator, country);
        gui.element().safeSendKeys(stateLocator, state);
        gui.element().safeSendKeys(cityLocator, city);
        gui.element().safeSendKeys(zipCodeLocator, zipCode);
        gui.element().safeSendKeys(mobileNumberLocator, mobileNumber);
        return this;
    }

    @Step("Click Create Account Button")
    public SignUpPage clickCreateAccountButton() {
        gui.element().safeClick(createAccountButtonLocator);
        return this;
    }

    @Step("Click on Continue Button")
    public NavigationBarComponent clickContinureButton()
    {
        gui.element().safeClick(continueButtonLocator);
        return new NavigationBarComponent(gui);
    }




    //***************************************************************************//

    //*************************   Validations    ********************************//

    public SignUpPage verifyAccountCreatedByLabel(String expectedLabel)
    {
        gui.verification().isElementVisible(accountCreatedLabelLocator);
        gui.verification().assertEquals(gui.element().safeGetText(accountCreatedLabelLocator), expectedLabel.toUpperCase(),"Error not same Header!");
        return this;
    }



    //**************************************************************************//


}



