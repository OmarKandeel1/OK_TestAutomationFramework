package com.automationexercices;

import driver.GUIDriver;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.automationexercices.components.NavigationBarComponent;
import pages.com.automationexercices.pages.ProductsPage;
import utils.dataReader.JsonReader;

@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("Cart Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Kandeel")
public class CartTests extends BaseTests {
    //****************************    Configurations    ****************************//
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("test-data/cart-data");
    }

    @BeforeMethod
    public void setUp() {
        gui = new GUIDriver();
        new NavigationBarComponent(gui).navigate();
    }

    @AfterMethod()
    public void tearDown() {
        if (gui != null) {
            gui.quit();
        }
    }

    //**************************************************************************//

    @Test
    @Description("Verify Cart functionality")
    public void verifyCartTC() {
        new ProductsPage(gui).navigate()
                .addProduct(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.addToCartSuccessMessage"))
                .clickOnViewCart()
                .verifyProductName(testData.getJsonData("product.name"))
                .verifyProductPrice(testData.getJsonData("product.name"), testData.getJsonData("product.price"))
                .verifyProductQuantity(testData.getJsonData("product.name"), testData.getJsonData("product.quantity"))
                .verifyProductTotalPrice(testData.getJsonData("product.name"), testData.getJsonData("product.total"));


    }


}
