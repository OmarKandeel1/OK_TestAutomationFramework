package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.dataReader.PropertyReader;
import utils.validations.Verification;

public class ProductDetailsPage {
    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public ProductDetailsPage(GUIDriver driver) {
        this.gui = driver;
    }
    //**************************************************************************//

    private final static String PRODUCT_END_POINT = "product_details/";
    //****************************    Helper Functions    ******************************//


    //*********************************************************************************//


    //****************************    Locators    ******************************//
    private final By productName = By.cssSelector(".product-information h2");
    private final By productPrice = By.cssSelector(".product-information span span");
    private final By name = By.id("name");
    private final By email = By.id("email");
    private final By review = By.id("review");
    private final By submitReviewButton = By.id("button-review");
    private final By reviewMsg = By.cssSelector("#review-section span");
    private final By addToCartLocator = By.cssSelector("button[type='button']");


    //**************************************************************************//


    //*************************   Methods    **********************************//
    @Step("Navigate to Product details page with id: {id}")
    public ProductDetailsPage navigate(String id) {
        gui.browser().goToURL(PropertyReader.getProperty("baseUrlWeb")+PRODUCT_END_POINT + id);
        return this;
    }

    @Step("Get Product Name")
    public String getProductName() {
        return gui.element().safeGetText(productName);
    }

    @Step("Get product Price")
    public String getProductPrice() {
        return gui.element().safeGetText(productPrice);
    }

    @Step("Add Review with name: {name} , email: {email} and review: {review}")
    public ProductDetailsPage addReview(String name, String email, String review) {
        gui.element().safeSendKeys(this.name, name)
                .safeSendKeys(this.email, email)
                .safeSendKeys(this.review, review)
                .safeClick(submitReviewButton);
        return this;
    }


    //**************************************************************************//

    //*************************   Validations    **********************************//
    @Step("Validate review msg with value: {expectedMsg}")
    public ProductDetailsPage validateReviewMsg(String expectedMsg)
    {
        gui.verification()
                .assertEquals(gui.element().safeGetText(reviewMsg), expectedMsg, "Error at review submission");
        return this;
    }

    @Step("Validating product details with {expectedName} , {expectedPrice}")
    public ProductDetailsPage validateProductDetails(String expectedName, String expectedPrice)
    {
        gui.verification()
                .assertEquals(gui.element().safeGetText(productName), expectedName, "Error at product name");
        gui.verification()
                .assertEquals(gui.element().safeGetText(productPrice), expectedPrice, "Error at product price");
        return this;
    }

    //*****************************************************************************//


}
