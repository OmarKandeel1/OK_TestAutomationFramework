package driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import utils.dataReader.PropertyReader;

public class BrowserOptions {
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");

        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")
                || PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")
        ) {
            options.addArguments("--headless");
        }

        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");

        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")
                || PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")
        ) {
            options.addArguments("--headless");
        }

        return options;
    }
}
