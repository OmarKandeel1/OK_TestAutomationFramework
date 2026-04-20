package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.internet.base.BasePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadPage extends BasePage {

    //****************************    Constructors    ****************************//
    public FileUploadPage(WebDriver driver) {
        super(driver);

    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By chooseFileLocator = By.id("file-upload");
    private By uploadButtonLocator = By.id("file-submit");
    private By uploadedFileLocator = By.id("uploaded-files");
    //**************************************************************************//


    //*************************   Methods    **********************************//

    public void clickOnUploadButton() {
        elementActions.safeClick(uploadButtonLocator);
    }

    public void clickOnChooseFileButton() {
        WebElement element = driver.findElement(chooseFileLocator);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].style.display='block';" +
                        "arguments[0].style.visibility='visible';" +
                        "arguments[0].style.opacity=1;" +
                        "arguments[0].style.width='1px';" +
                        "arguments[0].style.height='1px';" +
                        "arguments[0].scrollIntoView(true);",
                element
        );

        element.click(); // Now Edge should allow the click
    }

    public void uploadFile(String absoluteFilePath) {
        driver.findElement(chooseFileLocator).sendKeys(absoluteFilePath);
    }

    public String getUploadedFileText() {
        return driver.findElement(uploadedFileLocator).getText();
    }

    public void robotUploadFile(String absoluteFilePath) throws AWTException {
        Robot robot = new Robot();
        robot.setAutoDelay(100);
        robot.delay(1000);
        //do copy File Path to clipboard
        StringSelection stringSelection = new StringSelection(absoluteFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        //simulate Ctrl + V to paste the file path
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.delay(500);

        // Press Enter to select the file
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    //**************************************************************************//

}
