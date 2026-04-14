package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome extends AbstractDriver {

    @Override
    public WebDriver createDriver() {
        return new ChromeDriver(BrowserOptions.getChromeOptions());
    }
}
