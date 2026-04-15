package utils.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitManager;
import utils.logs.LogsManager;

public class FramesActions {
    private final WebDriver driver;
    private final WaitManager wait;

    public FramesActions(WebDriver driver, WaitManager wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void switchToFrameByIndex(int index) {
        wait.switchToFrame(index);
        LogsManager.info("Switched to frame by index: " + index);
    }

    public void switchToFrame(By locator) {
        wait.switchToFrame(locator);
        LogsManager.info("Switched to frame by locator: " + locator);
    }

    public void switchToFrame(WebElement frameElement) {
        wait.switchToFrame(frameElement);
        LogsManager.info("Switched to frame by WebElement: " + frameElement);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
        LogsManager.info("Switched to parent frame");
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        LogsManager.info("Switched to default content");
    }


}
