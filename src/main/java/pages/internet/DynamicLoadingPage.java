package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class DynamicLoadingPage extends BasePage {

    //****************************    Constructors    ****************************//
    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By example1Locator = By.linkText("Example 1: Element on page that is hidden");
    private By example2Locator = By.linkText("Example 2: Element rendered after the fact");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public DynamicLoadingEx1Page clickExample1() {
        driver.findElement(example1Locator).click();
        return new DynamicLoadingEx1Page(driver);
    }
    public DynamicLoadingEx2Page clickExample2() {
        driver.findElement(example2Locator).click();
        return new DynamicLoadingEx2Page(driver);
    }
    //**************************************************************************//

}
