package com.automationexercices;

import driver.GUIDriver;
import io.qameta.allure.*;
import org.testng.annotations.*;
import pages.com.automationexercices.apis.UserManagementAPI;
import pages.com.automationexercices.components.NavigationBarComponent;
import pages.com.automationexercices.pages.CartPage;
import pages.com.automationexercices.pages.ProductsPage;
import pages.com.automationexercices.pages.SignupLoginPage;
import utils.TimeManager;
import utils.dataReader.JsonReader;

@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("Checkout Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Kandeel")
public class CheckoutTests extends BaseTests {
    private String email;

    //****************************    Configurations    ****************************//
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("test-data/checkout-data");
        email = testData.getJsonData("email") + TimeManager.getSimpleTimeStamp() + "@gmail.com";
        gui = new GUIDriver();
        new NavigationBarComponent(gui).navigate();
    }

    @AfterClass()
    public void tearDown() {
        new UserManagementAPI().deleteUserAccount(200, email, testData.getJsonData("password"));
        if (gui != null) {
            gui.quit();
        }
    }


    //**************************************************************************//

    @Test
    @Description("Do User registeration using API call")
    public void registerUser() {

        new UserManagementAPI().createRegisterUserAccount(200,
                testData.getJsonData("name"),
                email,
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
                testData.getJsonData("day"),
                testData.getJsonData("month"),
                testData.getJsonData("year"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"),
                testData.getJsonData("company"),
                testData.getJsonData("address1"),
                testData.getJsonData("address2"),
                testData.getJsonData("country"),
                testData.getJsonData("postalCode"),
                testData.getJsonData("state"),
                testData.getJsonData("city"),
                testData.getJsonData("mobilePhone")
        );

    }

    @Test(dependsOnMethods = {"registerUser"})
    @Description("")
    public void login() {
        new SignupLoginPage(gui).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton();
        new NavigationBarComponent(gui).verifyUserLabelVisible(testData.getJsonData("name"));


    }

    @Test(dependsOnMethods = {"login", "registerUser"})
    @Description("")
    public void addProductToCart() {
        new ProductsPage(gui).navigate()
                .addProduct(testData.getJsonData("product.name"))
                .validateItemAddedLabel("Added!");

    }

    @Test(dependsOnMethods = {"login", "registerUser", "addProductToCart"})
    @Description("")
    public void checkout() {
        new CartPage(gui).navigate()
                .clickOnCheckoutButton()
                .verifyDeliveryAddressData(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("postalCode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobilePhone")
                ).clickPlaceOrderButton()
                .verifyPaymentPageLoaded();
    }


}
