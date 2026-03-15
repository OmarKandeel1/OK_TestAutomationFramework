package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

public class ShadowDomPage extends BasePage {
    //****************************    Constructors    ****************************//
    public ShadowDomPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By shadowHostsLocator = By.cssSelector("#content my-paragraph"); // must return 2 paragraphs host locator
    //**************************************************************************//


    //*************************   Methods    **********************************//


    public String getFirstParagraphTextNotInShadowDom() {
        return driver.findElements(shadowHostsLocator).get(0).findElement(By.cssSelector("span[slot = \"my-text\"]")).getText();
    }
    public String getFirstParagraphTextInShadowDom() {
        return driver.findElements(shadowHostsLocator).get(0).getShadowRoot().findElement(By.cssSelector("slot[name=\"my-text\"]")).getText();
    }
    //**************************************************************************//
}
