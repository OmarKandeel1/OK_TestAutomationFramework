package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ThreadGuard;
import utils.PropertyReader;

public class DriverFactory {

    private final static  String browser_ = PropertyReader.getProperty("browserType");
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


//    public static void initDriver(String browser) {
//
//
//        switch (browser.toLowerCase()) {
//
//
//            case "chrome":
//                driverThreadLocal.set(ThreadGuard.protect(new ChromeDriver(BrowserOptions.getChromeOptions())));
//                break;
//            case "edge":
//                driverThreadLocal.set(ThreadGuard.protect(new EdgeDriver(BrowserOptions.getEdgeOptions())));
//                break;
//
//            default:
//                throw new RuntimeException("Browser not supported");
//        }
//    }


    private static WebDriver getDriver()
    {
        Browser browserType = Browser.valueOf(browser_.toUpperCase());
        AbstractDriverFactory abstractDriver = browserType.getDriverFactory();
        return abstractDriver.createDriver();
    }

    public static WebDriver initDriver()
    {
        WebDriver driver = ThreadGuard.protect( getDriver());
        driverThreadLocal.set(driver);
        return driverThreadLocal.get();
    }



    public static void quitDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

}


