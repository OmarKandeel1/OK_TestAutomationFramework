package driver;

import org.openqa.selenium.WebDriver;
import utils.FileManager;
import utils.LocatorUtils;
import utils.actions.AlertActions;
import utils.BrowserManager;
import utils.actions.ElementActions;
import utils.actions.FramesActions;
import utils.WaitManager;
import utils.validations.Validation;
import utils.validations.Verification;

public class GUIDriver {

    private final WebDriver driver;
    private final WaitManager waitManager;

    public GUIDriver() {
        this(DriverFactory.initDriver());
    }

    public GUIDriver(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    public WebDriver get() {

        return driver;
    }

    public WaitManager waits() {
        return waitManager;
    }

    public ElementActions element() {
        return new ElementActions(get());
    }

    public BrowserManager browser() {
        return new BrowserManager(get());
    }

    public FramesActions frames() {
        return new FramesActions(get(), waits());
    }

    public AlertActions alerts() {
        return new AlertActions(get(), waits());
    }

    public Validation validation() {
        return new Validation(get(), waits());
    }

    public Verification verification() {
        return new Verification(get(), waits());
    }
    public FileManager file(){
        return new FileManager();
    }




    public void quit() {
        DriverFactory.quitDriver();
    }
}