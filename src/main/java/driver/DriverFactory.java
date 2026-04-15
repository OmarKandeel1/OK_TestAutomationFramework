package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

public class DriverFactory {

    private final static  String browser_ = PropertyReader.getProperty("browserType");
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();




    private static WebDriver getDriver()
    {
        Browser browserType = Browser.valueOf(browser_.toUpperCase());
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        LogsManager.info("Starting with borwser: "+browserType);
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


