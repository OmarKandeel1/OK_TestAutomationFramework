package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.dataReader.PropertyReader;

public class CartPage {

    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public CartPage(GUIDriver driver) {
        this.gui = driver;
    }

    //**************************************************************************//
    private final String CARTPAGE_ENDPOINT = "view_cart";

    //****************************   Dynamic Locators    ****************************//
    private String getProductRow(String productName) {
        return "//tr[.//a[normalize-space()='" + productName + "']]";
    }

    private By getProductName(String productName) {
        return By.xpath(getProductRow(productName) + "//td[@class = \"cart_description\"]//a");
    }

    private By getProductPrice(String productName) {
        return By.xpath(getProductRow(productName) + "//td[@class = \"cart_price\"]//p");
    }

    private By getProductQuantity(String productName) {
        return By.xpath(getProductRow(productName) + "//td[@class = \"cart_quantity\"]//button");
    }

    private By getProductTotal(String productName) {
        return By.xpath(getProductRow(productName) + "//td[@class = \"cart_total\"]//p");
    }

    private By getRemoveProductLocator(String productName) {
        return By.xpath(getProductRow(productName) + "//td[@class = \"cart_delete\"]//a");
    }


    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By checkoutButton = By.cssSelector(".btn.btn-default.check_out");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Navigate to Cart Page")
    public CartPage navigate() {
        gui.browser().goToURL(PropertyReader.getProperty("baseUrlWeb") + CARTPAGE_ENDPOINT);
        return this;
    }

    @Step("Click on checkout Button")
    public CheckoutPage clickOnCheckoutButton() {
        gui.element().safeClick(checkoutButton);
        return new CheckoutPage(gui);
    }

    @Step("Remove product: {productName}")
    public CartPage removeProduct(String productName) {
        gui.element().safeClick(getRemoveProductLocator(productName));
        return this;
    }


    //**************************************************************************//

    //*************************   Validations    **********************************//
    @Step("Validate Product name: {productName}")
    public CartPage verifyProductName(String productName) {
        gui.verification().assertEquals(gui.element().safeGetText(getProductName(productName)), productName, "Product name is not correct");
        return this;
    }

    @Step("Validate Product Price: {expectedPrice}")
    public CartPage verifyProductPrice(String productName, String expectedPrice) {
        gui.verification().assertEquals(gui.element().safeGetText(getProductPrice(productName)), expectedPrice, "Product price is not correct");
        return this;
    }

    @Step("Validate Product Quantity: {expectedQuantity}")
    public CartPage verifyProductQuantity(String productName, String expectedQuantity) {
        gui.verification().assertEquals(gui.element().safeGetText(getProductQuantity(productName)), expectedQuantity, "Product quantity is not correct");
        ;
        return this;
    }

    @Step("Validate Product Total Price: {expectedTotalPrice}")
    public CartPage verifyProductTotalPrice(String productName, String expectedTotalPrice) {
        gui.verification().assertEquals(gui.element().safeGetText(getProductTotal(productName)), expectedTotalPrice, "Product total price is not correct");
        return this;
    }


    //**************************************************************************//


}


