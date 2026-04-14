package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class JavaScriptAlertPage extends BasePage {

    //****************************    Constructors    ****************************//
    public JavaScriptAlertPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By alertTriggerButton = By.cssSelector("button[onclick *=\"Alert\"]");
    private By confirmTriggerButton = By.cssSelector("button[onclick *=\"Confirm\"]");
    private By promptTriggerButton = By.cssSelector("button[onclick *=\"Prompt\"]");
    private By resultMessage = By.id("result");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void clickOnAlertButton() {
        driver.findElement(alertTriggerButton).click();
    }

    public void clickOnConfirmButton() {
        driver.findElement(confirmTriggerButton).click();
    }

    public void clickOnPromptButton() {
        driver.findElement(promptTriggerButton).click();
    }

    public void jsDialogue_clickToAccept(){
        driver.switchTo().alert().accept();
    }

    public void jsDialogue_clickToDismiss(){
        driver.switchTo().alert().dismiss();
    }

    public String jsDialogue_getText(){
        return driver.switchTo().alert().getText();
    }

    public void jsDialogue_setText(String string_){
         driver.switchTo().alert().sendKeys(string_);
    }



    public String getResultText() {
        return driver.findElement(resultMessage).getText();
    }

    //**************************************************************************//

}
