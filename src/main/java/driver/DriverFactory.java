package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ThreadGuard;

public class DriverFactory {


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


    private static WebDriver getDriver(String browser_)
    {
        Browser browserType = Browser.valueOf(browser_.toUpperCase());
        AbstractDriverFactory abstractDriver = browserType.getDriverFactory();
        return abstractDriver.createDriver();
    }

    public static WebDriver initDriver(String browser_)
    {
        WebDriver driver = ThreadGuard.protect(getDriver(browser_));
        driverThreadLocal.set(driver);
        return driverThreadLocal.get();
    }



    public static void quitDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

}


