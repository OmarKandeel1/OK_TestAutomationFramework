package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class DragAndDropPage extends BasePage {

    //****************************    Constructors    ****************************//
    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By columnALocator = By.id("column-a");
    private By columnAHeaderLocator = By.cssSelector("#column-a header");
    private By columnBLocator = By.id("column-b");
    private By columnBHeaderLocator = By.cssSelector("#column-b header");
    //**************************************************************************//


    //*************************   Methods    ***********************************//
    public void dragAndDrop()
    {
        action.dragAndDrop(driver.findElement(columnALocator),driver.findElement(columnBLocator)).perform();
    }

    public String getColumnText()
    {
        return driver.findElement(columnALocator).getText();
    }

    //**************************************************************************//
}
