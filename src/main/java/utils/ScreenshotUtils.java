package utils;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    private  final static String SCREENSHOT_DIR = "screenshots/";
    private  final static String SCREENSHOT_EXT = ".png";




    public static void takeScreenshoot(WebDriver driver , String testName) {
        String filePath = SCREENSHOT_DIR + testName + "_" + System.currentTimeMillis() + SCREENSHOT_EXT;
        var camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(screenshot, new File(filePath));
        } catch (IOException e) {
            System.out.println("Moving screenshot Error!");
            throw new RuntimeException(e);
        }
    }


}
