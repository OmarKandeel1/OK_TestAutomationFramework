package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import utils.logs.LogsManager;

import java.util.Set;

public class BrowserManager {
    private final WebDriver driver;
    private final WebDriver.Navigation navigation;

    public BrowserManager(WebDriver driver_) {
        this.driver = driver_;
        this.navigation = driver.navigate();
    }


    public void goBack() {
        navigation.back();
        LogsManager.info("Navigated back");
    }

    public void goForward() {
        navigation.forward();
        LogsManager.info("Navigated forward");
    }


    public void goToURL(String url) {
        navigation.to(url);
        LogsManager.info("Navigated to URL: " + url);
    }

    public void refreshPage() {
        navigation.refresh();
        LogsManager.info("Page refreshed");
    }

    public void switchToTab(String title) {
        Set<String> windowHandles = driver.getWindowHandles(); //return all window handles for all tabs
        String originalHandle = driver.getWindowHandle();
        for (String handle : windowHandles) {
            if (driver.switchTo().window(handle).getTitle().trim().contains(title.trim())) {
                LogsManager.info("Switched to tab: " + title);
                return; // success
            }
        }

        // Restore to the original tab
        driver.switchTo().window(originalHandle);
        throw new RuntimeException("Tab with title " + title + " not found and i switched to the original tab!");
    }


    public void maximizeWindow() {
        driver.manage().window().maximize();
        LogsManager.info("Maximizing Window");
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LogsManager.info("Current URL: " + url);
        return url;
    }

    public String getTitle() {
        String title = driver.getTitle();
        LogsManager.info("Current Tab title " + title);
        return title;
    }

    public void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
        LogsManager.info("Opening new window");

    }

}
