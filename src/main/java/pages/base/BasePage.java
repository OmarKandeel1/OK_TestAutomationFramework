package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.actions.ElementActions;
import utils.WaitManager;
import utils.BrowserManager;

public class BasePage {
    protected WebDriver driver;
    protected WaitManager wait;
    protected BrowserManager windowManager;
    protected Actions action;
    protected ElementActions actionsbot;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitManager(driver);
        windowManager = new BrowserManager(driver);
        action  = new Actions(driver);
        actionsbot = new ElementActions(driver);
    }






}
