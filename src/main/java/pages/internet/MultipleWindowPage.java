package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class MultipleWindowPage extends BasePage {
    //****************************    Constructors    ****************************//
    public MultipleWindowPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By clickHereLocator = By.linkText("Click Here");
    //**************************************************************************//


    //*************************   Methods    **********************************//

    public NewWindowPage clickOnLink()
    {
        actionsbot.safeClick(clickHereLocator);
        return new NewWindowPage(driver);
    }

    //**************************************************************************//

}
