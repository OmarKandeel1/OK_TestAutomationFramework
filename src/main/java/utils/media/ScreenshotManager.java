package utils.media;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import utils.TimeManager;
import utils.logs.LogsManager;
import utils.report.AllureAttachmentManager;

import java.io.File;
import java.io.IOException;

public class ScreenshotManager {

    public static final String SCREENSHOT_DIR = "test-outputs/screenshots/";
    private static final String SCREENSHOT_EXT = ".png";

    // Take screenshot for entire page
    public static void takeScreenshot(WebDriver driver, String testName) {
        ensureScreenshotDirectoryExists();

        String filePath = SCREENSHOT_DIR
                + sanitizeFileName(testName)
                + "_"
                + TimeManager.getTimeStamp()
                + SCREENSHOT_EXT;

        TakesScreenshot camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        File destination = new File(filePath);

        try {
            Files.move(screenshot, destination);
            LogsManager.info("Screenshot saved: " + filePath);
            AllureAttachmentManager.attachScreenshots(testName, filePath);
        } catch (IOException e) {
            LogsManager.error("Error taking screenshot: " + e.getMessage());
            throw new RuntimeException("Failed to save screenshot to: " + filePath, e);
        }
    }

    // Take screenshot for specific element
    public static void takeElementScreenshot(WebDriver driver, By locator) {
        ensureScreenshotDirectoryExists();

        WebElement element = driver.findElement(locator);

        String elementName = element.getAccessibleName();
        if (elementName == null || elementName.isBlank()) {
            elementName = locator.toString();
        }

        elementName = sanitizeFileName(elementName);

        String filePath = SCREENSHOT_DIR
                + elementName
                + "_"
                + TimeManager.getTimeStamp()
                + SCREENSHOT_EXT;

        File screenshot = element.getScreenshotAs(OutputType.FILE);
        File destination = new File(filePath);

        try {
            Files.move(screenshot, destination);
            LogsManager.info("Element screenshot saved: " + filePath);
            AllureAttachmentManager.attachScreenshots(elementName, filePath);
        } catch (IOException e) {
            LogsManager.error("Error taking element screenshot: " + e.getMessage());
            throw new RuntimeException("Failed to save element screenshot to: " + filePath, e);
        }
    }

    private static void ensureScreenshotDirectoryExists() {
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Failed to create screenshot directory: " + SCREENSHOT_DIR);
        }
    }

    private static String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}