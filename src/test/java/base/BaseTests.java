package base;

import customlisteners.TestNGListeners;
import driver.DriverFactory;
import driver.WebDriverProvider;
import utils.media.ScreenshotManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.internet.HomePage;
import utils.*;
import utils.dataReader.PropertyReader;

@Listeners(TestNGListeners.class)
public class BaseTests implements WebDriverProvider {
    private WebDriver driver;
    protected HomePage homePage; //to use it in LoginTest when i inhert from this class
    protected BrowserManager windowManager;
  //  protected JsonReader jsonReader = new JsonReader("data");
    @BeforeClass
    public void setUp() {
        driver = DriverFactory.initDriver();
    }

    @BeforeMethod
    public void goHome(){
       driver.get("https://the-internet.herokuapp.com");
        windowManager= new BrowserManager(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void takeScreenshootOnFailure(ITestResult result)
    {
        if(ITestResult.FAILURE == result.getStatus())
        {
            ScreenshotManager.takeScreenshot(driver , result.getName());
        }
    }

    @AfterClass
    public void tearDown()
    {
        DriverFactory.quitDriver();
    }


    @Override
    public WebDriver getWebDriver() {
        return driver;
    }
}
