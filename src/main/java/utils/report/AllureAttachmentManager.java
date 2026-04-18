package utils.report;

import io.qameta.allure.Allure;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;
import utils.media.ScreenRecordManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;

public class AllureAttachmentManager {

    public static void attachScreenshots(String screenName, String screenPath) {
        try {
            File screenshotFile = new File(screenPath);
            Allure.addAttachment(screenName, newInputStream(Path.of(screenPath)));

        } catch (Exception e) {
            LogsManager.error("Failed to attach screenshot: " + e.getMessage());
        }
    }

    public static void attachLogs() {
        try {
            LogManager.shutdown();
            File logFile = new File(LogsManager.LOGS_PATH + File.separator + "logs.log");
            ((LoggerContext) LogManager.getContext(false)).reconfigure();

            if (logFile.exists()) {
                Allure.attachment("logs.log", Files.readString(logFile.toPath()));
            }
        } catch (Exception e) {
            LogsManager.error("Error attaching logs", e.getMessage());
        }
    }

    public static void attachRecords(String testName) {
        if (PropertyReader.getProperty("recordTests").equalsIgnoreCase("true")) {
            try {
                File record =new File(ScreenRecordManager.RECORDINGS_PATH + testName);
                if (record != null && record.getName().endsWith(".mp4")) {
                    Allure.addAttachment(testName, "video/mp4", Files.newInputStream(record.toPath()), ".mp4");
                }
            } catch (Exception e) {
                LogsManager.error("Error attaching records", e.getMessage());
            }
        }
    }


}
