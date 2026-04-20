package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

public class DriverFactory {

    private final static  String browser_ = PropertyReader.getProperty("browserType");
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();




    private DriverFactory() {
    }

    private static WebDriver createDriver() {
        String browserName = PropertyReader.getProperty("browserType");

        if (browserName == null || browserName.isBlank()) {
            throw new RuntimeException("browserType is missing in properties file.");
        }

        Browser browserType = Browser.valueOf(browserName.toUpperCase());
        AbstractDriver abstractDriver = browserType.getDriverFactory();

        LogsManager.info("Starting browser: " + browserType);
        return abstractDriver.createDriver();
    }

    public static WebDriver initDriver() {
        WebDriver driver = ThreadGuard.protect(createDriver());
        driverThreadLocal.set(driver);
        return driver;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

}


