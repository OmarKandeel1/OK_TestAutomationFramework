package utils;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static java.nio.file.Files.newInputStream;

public class AllureUtils {

    // Clean allure results folder
    public static void cleanAllureResults() {
        FileUtils.deleteQuietly(new File(PropertyReader.getProperty("allure.results.directory")));
    }

    public static void attachScreenshots(String screenName, String screenPath) {
        try {
            File screenshotFile = new File(screenPath);
            Allure.addAttachment(screenName, newInputStream(Path.of(screenPath)));

        } catch (Exception e) {
            System.out.println("Error taking SS at allure SS attach" + e.getMessage());
        }
    }

    public static void setAllureEnv() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", PropertyReader.getProperty("os.name"))
                        .put("JDK Version", PropertyReader.getProperty("java.runtime.version"))
                        .put("URL", "https://the-internet.herokuapp.com")
                        .build(), PropertyReader.getProperty("user.dir") + File.separator + "test-outputs/Allure-Reports/allure-results"
                        + File.separator
        );

    }

}
