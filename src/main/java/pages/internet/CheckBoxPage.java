package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.internet.base.BasePage;

public class CheckBoxPage extends BasePage {

    //****************************    Constructors    ****************************//
    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By checkboxesLocator = By.cssSelector("[type = \"checkbox\"]");

    //**************************************************************************//


    //*************************   Methods    **********************************//

    /**
     *
     * @param index indext start from 1
     */
    public void selectCheckBox(int index)
    {
        driver.findElements(checkboxesLocator).get(index-1).click();
    }
    public boolean isSelected(int index)
    {
        return driver.findElements(checkboxesLocator).get(index-1).isSelected();
    }
    //**************************************************************************//
}
