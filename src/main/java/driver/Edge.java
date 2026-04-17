package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

import java.net.URI;

public class Edge extends AbstractDriver {



    @Override
    public WebDriver createDriver() {
        String executionType = PropertyReader.getProperty("executionType").trim();

        if (executionType.equalsIgnoreCase("LocalHeadless")
                || executionType.equalsIgnoreCase("Local")) {
            return new EdgeDriver(BrowserOptions.getEdgeOptions());
        } else if (executionType.equalsIgnoreCase("remote")) {
            try {
                return new RemoteWebDriver(
                        new URI(PropertyReader.getProperty("remoteUrl").trim()).toURL(),
                        BrowserOptions.getChromeOptions()
                );
            } catch (Exception e) {
                LogsManager.error("Failed to create remote driver: " + e.getMessage());
                throw new RuntimeException("Failed to create remote driver", e);
            }
        } else {
            LogsManager.error("Unsupported execution type: " + executionType);
            throw new RuntimeException("Unsupported execution type: " + executionType);
        }
    }
}


