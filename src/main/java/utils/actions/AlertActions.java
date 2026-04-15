package utils.actions;

import org.openqa.selenium.WebDriver;
import utils.WaitManager;
import utils.logs.LogsManager;

public class AlertActions {
    private final WebDriver driver; //For future use if i needed to use lambda exp
    private final WaitManager waitManager;

    public AlertActions(WebDriver driver_, WaitManager waitManager_) {
        this.driver = driver_;
        this.waitManager = waitManager_;
    }

    public void acceptAlert() {
        waitManager.waitForAlert().accept();
        LogsManager.info("Accepted alert");
    }

    public void dismissAlert() {
        waitManager.waitForAlert().dismiss();
        LogsManager.info("Dismissed alert");
    }

    public String getAlertText() {
        String text = waitManager.waitForAlert().getText();
        LogsManager.info("Alert text: " + text);
        return text;
    }

    public void sendTextToAlert(String text) {
        waitManager.waitForAlert().sendKeys(text);
        LogsManager.info("Sent text to alert: " + text);
    }

}
