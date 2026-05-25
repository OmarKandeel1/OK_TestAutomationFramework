package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.LocatorUtils;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

public class PaymentPage {
    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public PaymentPage(GUIDriver driver) {
        this.gui = driver;
    }

    //**************************************************************************//
    private final static String PAYMENT_ENDPOINT = "payment";


    //****************************   Dynamic Locators    ****************************//


    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By nameOnCardLocator = LocatorUtils.byQa("name-on-card");
    private By cardNumberLocator = LocatorUtils.byQa("card-number");
    private By cvcLocator = LocatorUtils.byQa("cvc");
    private By expirationMonthLocator = LocatorUtils.byQa("expiry-month");
    private By expirationYearLocator = LocatorUtils.byQa("expiry-year");
    private By payAndConfirmOrderButtonLocator = LocatorUtils.byQa("pay-button");
    private By successMsgLocator = By.cssSelector("#success_message .alert-success.alert");
    private By downloadInvoiceButton = By.xpath("//a[contains(@href, 'download_invoice')]");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Navigate to Payment Page")
    public PaymentPage navigate() {
        gui.browser().goToURL(PropertyReader.getProperty("baseUrlWeb") + PAYMENT_ENDPOINT);
        return this;
    }

    @Step("Filling payment info with Name: {name}, Card Number: {cardNumber}, CVC: {cvc}, Expiration Month: {expMonth}, Expiration Year: {expYear}")
    public PaymentPage fillPaymentData(String name ,String cardNumber, String cvc, String expMonth,String expYear)
    {
        gui.element().safeSendKeys(nameOnCardLocator, name)
                .safeSendKeys(cardNumberLocator, cardNumber)
                .safeSendKeys(cvcLocator, cvc)
                .safeSendKeys(expirationMonthLocator, expMonth)
                .safeSendKeys(expirationYearLocator, expYear);
        return this;
    }

    @Step("Click on Pay and Confirm Order Button")
    public PaymentSuccessPage clickOnPayAndConfirmOrderButton()
    {
        gui.element().safeClick(payAndConfirmOrderButtonLocator);
        return new PaymentSuccessPage(gui);
    }


    //**************************************************************************//

    //*************************   Validations    **********************************//

    public PaymentPage verifyPaymentPageLoaded() {
        String currentUrl = gui.browser().getCurrentUrl();
        if(currentUrl.contains("google_vignette")) {
            gui.browser().goBack();
            LogsManager.info("Navigated back from Google Vignette ad to Payment page");
        }
        currentUrl = gui.browser().getCurrentUrl();
        gui.verification().assertTrue(currentUrl.contains("payment"), "Payment page URL does not contain 'payment'");

        return new PaymentPage(gui);
    }

    public PaymentPage validateSuccessMessage(String expectedMsg)
    {
    gui.verification().assertEquals(gui.element().safeGetText(successMsgLocator), expectedMsg, "Success message text does not match expected value");
    return this;
    }
    //**************************************************************************//
}
