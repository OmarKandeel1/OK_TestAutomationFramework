package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowManager {
    private WebDriver driver;
    private WebDriver.Navigation navigation;

    public WindowManager(WebDriver driver_) {
        this.driver = driver_;
        this.navigation = driver.navigate();
    }


    public void goBack() {
        navigation.back();
    }

    public void goForward() {
        navigation.forward();
    }

    public void goToURL(String url) {
        navigation.to(url);
    }

    public void refreshPage() {
        navigation.refresh();
    }

    public void switchToTab(String title) {
        Set<String> windowHandles = driver.getWindowHandles(); //return all window handles for all tabs
        String originalHandle = driver.getWindowHandle();
        for(String handle:windowHandles)
        {
            if(driver.switchTo().window(handle).getTitle().trim().contains(title.trim()))
            {
                return; // success
            }
        }

        // Restore to the original tab
        driver.switchTo().window(originalHandle);
        throw new RuntimeException("Tab with title "+ title + " not found and i switched to the original tab!");
    }

}
