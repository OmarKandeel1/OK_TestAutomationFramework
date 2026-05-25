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
@Story("Products Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Kandeel")
public class ProductsTest extends BaseTests {

    //****************************    Configurations    ****************************//
    @BeforeClass
    public void beforeClass() {
        testData = new JsonReader("test-data/product-data");
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
    @Description("Search for product")
    public void searchForProduct(){
       new ProductsPage(gui).navigate()
               .searchForProduct(testData.getJsonData("searchProduct.name"))
               .verifyProductNameAndPrice(testData.getJsonData("searchProduct.name"), testData.getJsonData("searchProduct.price"));
    }

    @Test
    @Description("Add product to cart without Login")
    public void addProductToCartWithoutLogin()
    {
        new ProductsPage(gui).navigate()
                .addProduct(testData.getJsonData("product1.name"))
                .validateItemAddedLabel("Added!");
    }

}
