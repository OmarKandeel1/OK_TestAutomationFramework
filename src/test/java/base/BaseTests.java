package base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.internet.HomePage;
import utils.*;
import utils.dataReader.JsonReader;
import utils.dataReader.PropertyReader;


public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage; //to use it in LoginTest when i inhert from this class
    protected BrowserManager windowManager;
    protected JsonReader jsonReader = new JsonReader("data");
    @BeforeClass
    public void setUp() {
        PropertyReader.loadProperties();
        AllureUtils.cleanAllureResults();
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
            ScreenshotUtils.takeScreenshoot(driver , result.getName());
        }
    }

    @AfterClass
    public void tearDown()
    {
        AllureUtils.setAllureEnv();
        DriverFactory.quitDriver();
    }


}
