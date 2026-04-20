package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.internet.base.BasePage;

public class NestedFramesPage extends BasePage {

    //****************************    Constructors    ****************************//
    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    String topFrame = "frame-top";
    String top_leftFrame = "frame-left";
    String bottomFrame = "frame-bottom";
    By bodyElement = By.tagName("body");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    public String getLeftFrameText() {
        driver.switchTo().frame(topFrame);
        driver.switchTo().frame(top_leftFrame);
        String text = driver.findElement(bodyElement).getText();
        driver.switchTo().defaultContent(); // Jump to most parent
        return text;
    }

    public String getBottomFrameText() {
        driver.switchTo().frame(bottomFrame);
        String text = driver.findElement(bodyElement).getText();
        driver.switchTo().defaultContent(); // Jump to most parent
        return text;
    }


    //**************************************************************************//
}
