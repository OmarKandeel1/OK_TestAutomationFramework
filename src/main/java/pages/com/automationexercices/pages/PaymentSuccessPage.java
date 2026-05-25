package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.WaitManager;

public class PaymentSuccessPage {
    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public PaymentSuccessPage(GUIDriver driver) {
        this.gui = driver;
    }

    //**************************************************************************//


    //****************************   Dynamic Locators    ****************************//


    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By SuccessMsgLocator = By.cssSelector("h2[data-qa] + p ");
    private By downloadInvoiceButton = By.cssSelector("a.btn.check_out");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Click on download invoice")
    public PaymentSuccessPage clickOnDownloadInvoice()
    {
        gui.element().safeClick(downloadInvoiceButton);
        return this;
    }





    //**************************************************************************//

    //*************************   Validations    **********************************//
    @Step("Verify Order placed msg")
    public PaymentSuccessPage verifyOrderPlacedMsg(String expectedMsg)
    {
    gui.verification().assertEquals(gui.element().safeGetText(SuccessMsgLocator), expectedMsg, "Order placed message is not as expected");
    return this;
    }

    public PaymentSuccessPage verifyInvoiceDownloaded(String expectedFileName)
    {
        gui.waits().fluentWait().until(driver -> gui.file().isFileDownloaded(expectedFileName));
        gui.verification().assertTrue(gui.file().isFileDownloaded(expectedFileName), "Invoice file was not downloaded successfully");
        return this;
    }


    //**************************************************************************//
}
