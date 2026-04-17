package utils.media;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import utils.AllureUtils;
import utils.TimeManager;
import utils.logs.LogsManager;

import java.io.File;
import java.io.IOException;

public class ScreenshotManager {

    private final static String SCREENSHOT_DIR = "test-outputs/screenshots/";
    private final static String SCREENSHOT_EXT = ".png";


    //Take SS for the entire page
    public static void takeScreenshot(WebDriver driver, String testName) {
        String filePath = SCREENSHOT_DIR + testName + "_" + TimeManager.getTimeStamp() + SCREENSHOT_EXT;
        var camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(screenshot, new File(filePath));
            LogsManager.info("Screenshot saved: " + filePath);
            AllureUtils.attachScreenshots(testName, filePath);
        } catch (IOException e) {
            LogsManager.error("Error Taking Screenshot!" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Take SS for specific element
    public static void takeElementScreenshot(WebDriver driver, By locator_) {
        WebElement element = driver.findElement(locator_);

        String elementName = element.getAccessibleName();
        if (elementName == null || elementName.isBlank()) {
            elementName = locator_.toString();
        }

        String filePath = SCREENSHOT_DIR
                + sanitizeFileName(elementName)
                + "_"
                + TimeManager.getTimeStamp()
                + SCREENSHOT_EXT;

        File screenshot = element.getScreenshotAs(OutputType.FILE);
        // TODO: attach the SS to Allure

        try {
            Files.move(screenshot, new File(filePath));
            LogsManager.info("Screenshot saved: " + filePath);
        } catch (IOException e) {
            LogsManager.error("Error taking element screenshot: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

}
