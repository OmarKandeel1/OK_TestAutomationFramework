package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class HomePage extends BasePage {

    //****************************    Constructors    ****************************//
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//

    //**************************************************************************//


    //*************************   Methods    **********************************//
    private void clickLink(String linkText_) {
        driver.findElement(By.linkText(linkText_)).click();
    }

    public LoginPage clickFormAuth() {
        clickLink("Form Authentication");
        return new LoginPage(driver);
    }

    public DropDownPage clickDropDown() {
        clickLink("Dropdown");
        return new DropDownPage(driver);
    }

    public HoversPage clickHoversLink() {
        clickLink("Hovers");
        return new HoversPage(driver);
    }

    public KeyPressesPage clickKeyPressLink() {
        clickLink("Key Presses");
        return new KeyPressesPage(driver);
    }

    public JavaScriptAlertPage clickJavaScriptAlertLink() {
        clickLink("JavaScript Alerts");
        return new JavaScriptAlertPage(driver);
    }

    public FileUploadPage clickFileUploadLink() {
        clickLink("File Upload");
        return new FileUploadPage(driver);
    }

    public EntryAddPage clickEntryAddLink() {
        clickLink("Entry Ad");
        return new EntryAddPage(driver);
    }

    public FramesPage clickFramesLink() {
        clickLink("Frames");
        return new FramesPage(driver);
    }

    public DynamicLoadingPage clickDynamicLoadingLink() {
        clickLink("Dynamic Loading");
        return new DynamicLoadingPage(driver);
    }

    public LargeAndDeepDomPage clickLargeAndDeepDomLink() {
        clickLink("Large & Deep DOM");
        return new LargeAndDeepDomPage(driver);
    }

    public InfiniteScrollPage clickInfiniteScrollLink() {
        clickLink("Infinite Scroll");
        return new InfiniteScrollPage(driver);
    }

    public MultipleWindowPage clickMultipleWindowLink() {
        clickLink("Multiple Windows");
        return new MultipleWindowPage(driver);
    }


    public BasicAuthPage clickBasicAuthLink() {
        clickLink("Basic Auth");
        return new BasicAuthPage(driver);
    }
    public ShadowDomPage clickShadowDOMLink() {
        clickLink("Shadow DOM");
        return new ShadowDomPage(driver);
    }
    public CheckBoxPage clickCheckboxesLink() {
        clickLink("Checkboxes");
        return new CheckBoxPage(driver);
    }
    public ContextMenuPage clickContextMenuPageLink() {
        clickLink("Context Menu");
        return new ContextMenuPage(driver);
    }
    public DragAndDropPage clickDragAndDropPageLink() {
        clickLink("Drag and Drop");
        return new DragAndDropPage(driver);
    }


    //**************************************************************************//

}
