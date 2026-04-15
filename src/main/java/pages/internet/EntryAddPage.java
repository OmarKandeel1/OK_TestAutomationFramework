package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class EntryAddPage extends BasePage {

    //****************************    Constructors    ****************************//
    public EntryAddPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By closeModalLocator = By.xpath("//div[@class = \"modal\"]//p[contains(. , 'Close')]");
    private By entryAdLocator = By.xpath("//h3[contains(. , 'Entry')]");
    private By clickHereLinkLocator = By.xpath("//a[@id='restart-ad']");
    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void closeModal() {
        //Must wait
       // driver.findElement(closeModalLocator).click();
        //Now we will use SafeClick
        actionsbot.safeClick(closeModalLocator);
    }

    public String getEntryAddText() {
        return driver.findElement(entryAdLocator).getText();
    }

    public void clickOnLink() {
        //driver.findElement(clickHereLinkLocator).click();
        actionsbot.safeClick(clickHereLinkLocator);
    }

    public boolean isClickOnLinkClickable() {
        try {
            WebElement link = driver.findElement(clickHereLinkLocator);

            return link.isDisplayed() && link.isEnabled();

            // Optional: stricter check (but not perfect)
            // return link.isDisplayed() && link.isEnabled() && link.getSize().getWidth() > 0;
        } catch (NoSuchElementException e) {
            return false;  // not even present
        }
    }


    //**************************************************************************//
}
