package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {


    public static WebDriver getDriver(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                return new ChromeDriver(BrowserOptions.getChromeOptions());

            case "edge":
                return new EdgeDriver(BrowserOptions.getEdgeOptions());

            default:
                throw new RuntimeException("Browser not supported");
        }
    }
}

