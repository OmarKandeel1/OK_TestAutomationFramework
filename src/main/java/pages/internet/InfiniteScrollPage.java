package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.internet.base.BasePage;

public class InfiniteScrollPage extends BasePage {
    //****************************    Constructors    ****************************//
    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By paragraphsLocator = By.className("jscroll-added");


    //**************************************************************************//


    //*************************   Methods    **********************************//
    public void scrollToParagraph(int index){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollTo(0,document.body.scrollHeight)";

        while(getNoOfParagraphs() < index)
        {
            js.executeScript(script);
        }

    }

    /**
     *
     * @param index it starts from 1
     * @return
     */
    public String getParagraphText(int index)
    {
        By paragraphLocator = By.xpath("(//div[@class = \"jscroll-added\"])[" + index + "]");
        wait.waitForElementTextNotToContain(paragraphLocator,"Loading...");
        return driver.findElements(paragraphsLocator).get(index-1).getText();

    }

    private int getNoOfParagraphs(){
    return driver.findElements(paragraphsLocator).size();
    }
    //**************************************************************************//
}
