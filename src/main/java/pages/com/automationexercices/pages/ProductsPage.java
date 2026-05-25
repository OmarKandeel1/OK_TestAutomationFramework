package pages.com.automationexercices.pages;

import driver.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.dataReader.PropertyReader;

public class ProductsPage {

    private final GUIDriver gui;

    //****************************    Constructors    ****************************//
    public ProductsPage(GUIDriver driver) {
        this.gui = driver;
    }
    //**************************************************************************//
    private final static String PRODUCT_END_POINT = "products";
    //****************************    Helper Functions    ******************************//
    private By getProductByName(String productName)
    {
        return By.xpath("//div[@class = \"productinfo text-center\"] //p[contains(. , \"" + productName + "\")]");
    }

    private By productHover(String productName)
    {
        return By.xpath("//div[@class = \"productinfo text-center\"][contains(. , \"" + productName + "\")]");
    }

    private By getProductPriceByName(String productName)
    {
        return By.xpath("//div[@class = \"productinfo text-center\"] //p[contains(. , \"" + productName + "\")] /preceding-sibling::h2");
    }

    private By addToCartByName(String productName)
    {
        return By.xpath("//div[@class = \"product-overlay\"] //p[contains(. , \"" + productName + "\")] //following-sibling::a");
    }

    private By getViewProductByName(String productName)
    {
        return By.xpath("//div[@class='product-image-wrapper'][.//p[contains(text(),'" + productName + "')]]//a[contains(text(),'View Product')]");
    }

    //*********************************************************************************//



    //****************************    Locators    ******************************//
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By addedLabelLocator = By.xpath("//div[@class=\"modal-header\"]//h4");
    private final By viewCartLocator = By.xpath("//div[@class=\"modal-body\"]//u");
    private final By continueShoppingLocator = By.xpath("//div[@class=\"modal-footer\"]//button");

    //**************************************************************************//





    //*************************   Methods    **********************************//
    @Step("Navigate to Product Page")
    public ProductsPage navigate()
    {        gui.browser().goToURL(PropertyReader.getProperty("baseUrlWeb") + PRODUCT_END_POINT);
        return this;
    }

    @Step("Add product to cart its name: {productName}")
    public ProductsPage addProduct(String productName)
    {
      gui.element().hover(productHover(productName));
      gui.element().safeClick(addToCartByName(productName));
      return this;
    }

    @Step("Click on view product for product: {productName}")
    public ProductDetailsPage clickOnViewProduct(String productName)
    {
        gui.element().hover(getProductByName(productName));
        gui.element().safeClick(getViewProductByName(productName));
        return new ProductDetailsPage(gui);
    }

    @Step("Click on View Cart")
    public CartPage clickOnViewCart()
    {
        gui.element().safeClick(viewCartLocator);
        return new CartPage(gui);
    }

    @Step("Search for product: {productName}")
    public ProductsPage searchForProduct(String productName)
    {
        gui.element().safeSendKeys(searchInput, productName);
        gui.element().safeClick(searchButton);
        return this;
    }



    //**************************************************************************//

    //*************************   Validations    **********************************//
    public ProductsPage verifyLabel(String expectedMsg)
    {
        gui.verification().assertEquals(gui.element().safeGetText(addedLabelLocator), expectedMsg,"Error at product adding");
        return this;
    }

    @Step("Validate product details for {productName} with price {productPrice}")
    public ProductsPage verifyProductNameAndPrice(String productName , String productPrice)
    {
        gui.element().hover(getProductByName(productName));
        gui.element().getText(getProductByName(productName));
        gui.element().getText(getProductPriceByName(productName));
        gui.validation().assertEquals(gui.element().hover(getProductByName(productName)).getText(getProductByName(productName)), productName, "Error at product name");
        gui.validation().assertEquals(gui.element().hover(getProductByName(productName)).getText(getProductPriceByName(productName)), productPrice, "Error at product price");
        return this;
    }

    @Step("Validate item added label contains {expectedText}")
    public ProductsPage validateItemAddedLabel(String expectedText)
    {
        String actualText = gui.element().safeGetText(addedLabelLocator);
        gui.validation().assertTrue(actualText.contains(expectedText), "Error at item added label");
        return this;
    }


    //*****************************************************************************//



}

