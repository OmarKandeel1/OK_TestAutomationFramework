package base;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utils.ScreenshotUtils;
import utils.WaitManager;
import utils.WindowManager;

import java.sql.Driver;
import java.util.List;


public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage; //to use it in LoginTest when i inhert from this class
    protected WindowManager windowManager;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.initDriver("edge");
    }

    @BeforeMethod
    public void goHome(){
       driver.get("https://the-internet.herokuapp.com");
        windowManager= new WindowManager(driver);
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
        DriverFactory.quitDriver();
    }


}
