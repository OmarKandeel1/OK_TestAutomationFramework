package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FramesPage extends BasePage {

    //****************************    Constructors    ****************************//
    public FramesPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By nestedFramesLink = By.linkText("Nested Frames");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public NestedFramesPage clickOnNestedFrames()
    {
        driver.findElement(nestedFramesLink).click();
        return new NestedFramesPage(driver);
    }

    //**************************************************************************//
}
