package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.dataReader.PropertyReader;

public class CheckoutPage {

    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public CheckoutPage(GUIDriver driver) {
        this.gui = driver;
    }

    //**************************************************************************//
    private static final String CHECKOUT_ENDPOINT = "checkout";

    //****************************   Dynamic Locators    ****************************//


    //**************************************************************************//


    //****************************    Locators    ****************************//
    private final By totalAmountLocator = By.xpath("//td[@colspan]/following-sibling::td//p");
    private final By deliveryName = By.cssSelector("#address_delivery .address_firstname.address_lastname");
    private final By deliveryCompany = By.xpath("(//ul[@id='address_delivery'] //li[@class='address_address1 address_address2'])[1]");
    private final By deliveryAddress1 = By.xpath("(//ul[@id='address_delivery'] //li[@class='address_address1 address_address2'])[2]");
    private final By deliveryAddress2 = By.xpath("(//ul[@id='address_delivery'] //li[@class='address_address1 address_address2'])[3]");
    private final By deliveryCityStatePostalLocator = By.xpath("//ul[@id='address_delivery'] //li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountryNameLocator = By.xpath("//ul[@id='address_delivery'] //li[@class='address_country_name']");
    private final By deliveryPhoneNumberLocator = By.xpath("//ul[@id='address_delivery'] //li[@class='address_phone']");
    private final By placeOrderButtonLocator = By.cssSelector(".btn.btn-default.check_out");


    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Navigate to Checkout page")
    public CheckoutPage navigate() {
        gui.browser().goToURL(PropertyReader.getProperty("baseUrlWeb") + CHECKOUT_ENDPOINT);
        return this;
    }

    @Step("Click on place order Button")
    public PaymentPage clickPlaceOrderButton() {
        gui.element().scrollToElementJS(placeOrderButtonLocator).safeClick(placeOrderButtonLocator);
        return new PaymentPage(gui);
    }


    //**************************************************************************//

    //*************************   Validations    **********************************//
    @Step("Verify Delivery Address Data : Gender:{gender} ,FirstName: {fName}, LastName:{lName}," +
            " Compant: {company}, Add1:{addr1}, Add2:{addr2}, city: {city}, state:{state}, zipcode:{zip}, Country:{country}, Phone:{phone}")
    public CheckoutPage verifyDeliveryAddressData(String gender, String fName, String lName, String company,
                                                  String addr1, String addr2, String city, String state,
                                                  String zip, String country, String phone) {
        gui.verification().assertEquals(gui.element().safeGetText(deliveryName), gender + ". " + fName + " " + lName, "Error at the Name")
                .assertEquals(gui.element().safeGetText(deliveryCompany), company, "Error at the Company")
                .assertEquals(gui.element().safeGetText(deliveryAddress1), addr1, "Error at the Address1")
                .assertEquals(gui.element().safeGetText(deliveryAddress2), addr2, "Error at the Address2")
                .assertEquals(gui.element().safeGetText(deliveryCityStatePostalLocator), city + " " + state + " " + zip, "Error at the City, State and Postal Code")
                .assertEquals(gui.element().safeGetText(deliveryCountryNameLocator), country, "Error at the Country")
                .assertEquals(gui.element().safeGetText(deliveryPhoneNumberLocator), phone, "Error at the Phone Number");
        return this;
    }




    //**************************************************************************//
}
