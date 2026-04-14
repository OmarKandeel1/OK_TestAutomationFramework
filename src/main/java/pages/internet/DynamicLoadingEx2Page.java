package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class DynamicLoadingEx2Page extends BasePage {
    //****************************    Constructors    ****************************//
    public DynamicLoadingEx2Page(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By startButton = By.cssSelector("#start button");
    private By helloWorldLocator = By.cssSelector("#finish h4");
    private By loadingBarLocator = By.cssSelector("#loading img");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void clickOnStartButton() {
        driver.findElement(startButton).click();
        wait.waitForInvisibility(loadingBarLocator);
    }

    public String getHelloWorldText() {
        wait.waitForVisibility(helloWorldLocator);
        return driver.findElement(helloWorldLocator).getText();
    }
    //**************************************************************************//
}
