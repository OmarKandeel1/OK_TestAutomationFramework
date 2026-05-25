package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import utils.dataReader.PropertyReader;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> prefs = new HashMap<>();

        String userDir = System.getProperty("user.dir");
        String downloadPath = userDir + "\\src\\test\\resources";

        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", downloadPath);

        options.setExperimentalOption("prefs", prefs);

        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

        options.setCapability(
                CapabilityType.ACCEPT_INSECURE_CERTS,
                true
        );

        options.setCapability(
                CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE
        );

        options.setCapability(
                CapabilityType.ENABLE_DOWNLOADS,
                true
        );

        options.setAcceptInsecureCerts(true);

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless")
                || PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")
        ) {
            options.addArguments("--headless");
        }

        return options;
    }
}
