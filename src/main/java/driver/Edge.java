package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Edge extends AbstractDriver {
    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(BrowserOptions.getEdgeOptions());
    }
}
