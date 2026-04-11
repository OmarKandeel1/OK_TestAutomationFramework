package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeFactory extends AbstractDriverFactory{
    @Override
    public WebDriver createDriver() {
        return new EdgeDriver(BrowserOptions.getEdgeOptions());
    }
}
