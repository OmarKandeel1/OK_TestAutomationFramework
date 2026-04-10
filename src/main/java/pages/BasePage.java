package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.ActionBot;
import utils.WaitManager;
import utils.WindowManager;

public class BasePage {
    protected WebDriver driver;
    protected WaitManager wait;
    protected WindowManager windowManager;
    protected Actions action;
    protected ActionBot actionsbot;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitManager(driver);
        windowManager = new WindowManager(driver);
        action  = new Actions(driver);
        actionsbot = new ActionBot(driver);
    }






}
