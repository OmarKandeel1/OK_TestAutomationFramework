package pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

public class HoversPage extends BasePage {

    //****************************    Constructors    ****************************//
    public HoversPage(WebDriver driver) {
        super(driver);
    }
    //**************************************************************************//


    //****************************    Locators    ****************************//
    private By figureBoxes = By.className("figure");
    private By figureCaption = By.className("figcaption");
    //**************************************************************************//


    //*************************   Methods    **********************************//

    /**
     *
     * @param index starts at 1
     */
    public FigureCaption hoverOverFigure(int index) {
        WebElement figure = driver.findElements(figureBoxes).get(index - 1);
        action.moveToElement(figure).perform();
        return new FigureCaption(figure.findElement(figureCaption));
    }

    //**************************************************************************//

    //*************************   Inner Class    *******************************//
    public  class FigureCaption {
        private WebElement captionElement;
        private By header = By.tagName("h5");
        private By link = By.tagName("a");

        public FigureCaption(WebElement captionElement_) {
            this.captionElement = captionElement_;
        }

        public boolean isCaptionDisplayed() {
            return captionElement.isDisplayed();
        }

        public String getfigureText() {
            return captionElement.findElement(header).getText();
        }

        public String getLinkText() {
            return captionElement.findElement(link).getText();
        }

        public String getLink() {
            return captionElement.findElement(link).getAttribute("href");
        }



    }


    //**************************************************************************//

}
