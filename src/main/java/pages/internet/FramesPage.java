package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class FramesPage extends BasePage {

    //****************************    Constructors    ****************************//
    public FramesPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By nestedFramesLink = By.linkText("Nested Frames");
    private By iFramesLink = By.linkText("iFrame");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public NestedFramesPage clickOnNestedFrames()
    {
        driver.findElement(nestedFramesLink).click();
        return new NestedFramesPage(driver);
    }
    public NestedFramesPage clickOnIFrames()
    {
        driver.findElement(iFramesLink).click();
        return new NestedFramesPage(driver);
    }

    //**************************************************************************//
}
