package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.internet.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class DropDownPage extends BasePage {

    //****************************    Constructors    ****************************//
    public DropDownPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By dropDownElement = By.id("dropdown");

    //**************************************************************************//


    //*************************   Methods    **********************************//
    private Select findDropDownElement() {
        return new Select(driver.findElement(dropDownElement));
    }

    public void selectFromDropDown(String value_) {
        findDropDownElement().selectByValue(value_);
    }

    public List<String> getSelectedOptionsText() {
        List<WebElement> allSelectedOptions = findDropDownElement().getAllSelectedOptions();
        List<String> allSelectedOptionsText = new ArrayList<>();

        for (WebElement element : allSelectedOptions) {
            allSelectedOptionsText.add(element.getText());
        }

        return allSelectedOptionsText;
    }

    public boolean getMultipleState()
    {
        return  findDropDownElement().isMultiple();
    }


    //**************************************************************************//


}
