package utils.report;

import org.apache.commons.io.FileUtils;
import org.testng.util.TimeUtils;
import utils.FileManager;
import utils.OSUtils;
import utils.TerminalUtils;
import utils.TimeManager;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static utils.report.AllureConstants.HISTORY_FOLDER;
import static utils.report.AllureConstants.RESULTS_HISTORY_FOLDER;

public class AllureReportGenerator {

    //Generate Allure Report
    public static boolean generateReports(boolean isSingleFile) {
        if (!AllureBinaryManager.downloadAndExtract()) {
            LogsManager.error("Allure report generation skipped because Allure commandline is not available.");
            return false;
        }

        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;

        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));

        if (isSingleFile) command.add("--single-file");

        return TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }


    //open Allure report in browser
    public static void openReport(String reportFileName) {
        if (!PropertyReader.getProperty("OpenAllureReportAfterExecution").equalsIgnoreCase("true")) return;

        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.getCurrentOS()) {
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", "", reportPath.toString());
            case MAC, LINUX -> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }


    //copy history folder to results folder
    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogsManager.error("Error copying history files", e.getMessage());
        }
    }


    //rename report file with timestamp
//rename report file to AllureReport_timestamp.html
    public static String renameReport() {
        String newFileName = AllureConstants.REPORT_PREFIX
                + TimeManager.getTimeStamp()
                + AllureConstants.REPORT_EXTENSION;

        File oldFile = AllureConstants.REPORT_PATH
                .resolve(AllureConstants.INDEX_HTML)
                .toFile();

        File newFile = AllureConstants.REPORT_PATH
                .resolve(newFileName)
                .toFile();

        try {
            if (!oldFile.exists()) {
                LogsManager.error("index.html not found for renaming: " + oldFile.getAbsolutePath());
                return AllureConstants.INDEX_HTML;
            }

            FileUtils.moveFile(oldFile, newFile); // 🔥 THIS IS THE FIX

            LogsManager.info("Report renamed to: " + newFile.getAbsolutePath());

            return newFileName;

        } catch (Exception e) {
            LogsManager.error("Failed to rename report: " + e.getMessage());
            return AllureConstants.INDEX_HTML; // fallback
        }
    }
}
