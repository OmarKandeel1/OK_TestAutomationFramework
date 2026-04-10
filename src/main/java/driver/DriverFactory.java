package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {


    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public static void initDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                driverThreadLocal.set(new ChromeDriver(BrowserOptions.getChromeOptions()));
                break;
            case "edge":
                driverThreadLocal.set(new EdgeDriver(BrowserOptions.getEdgeOptions()));
                break;

            default:
                throw new RuntimeException("Browser not supported");
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

}


