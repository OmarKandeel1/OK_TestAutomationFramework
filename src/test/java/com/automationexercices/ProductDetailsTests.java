package com.automationexercices;

import driver.GUIDriver;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.automationexercices.components.NavigationBarComponent;
import pages.com.automationexercices.pages.ProductDetailsPage;
import pages.com.automationexercices.pages.ProductsPage;
import utils.dataReader.JsonReader;

@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("Products Details Management")
@Severity(SeverityLevel.NORMAL)
@Owner("Kandeel")
public class ProductDetailsTests extends BaseTests {


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
    @Description("Verifying Product Details")
    public void verifyProductDetailsTC()
    {
        new ProductsPage(gui).navigate()
                .clickOnViewProduct(testData.getJsonData("product1.name"))
                .validateProductDetails(testData.getJsonData("product1.name"), testData.getJsonData("product1.price"));
    }

    @Test
    @Description("Validating the review is submited correctly")
    public void verifyReviewAdding()
    {
        new ProductDetailsPage(gui).navigate("2")
                .addReview("Omar" , "Omar@m.com" , "HI hIH")
                .validateReviewMsg("Thank you for your review.");

    }

}
