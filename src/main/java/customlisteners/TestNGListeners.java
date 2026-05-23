package customlisteners;

import driver.WebDriverProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import utils.FileManager;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;
import utils.media.ScreenRecordManager;
import utils.media.ScreenshotManager;
import utils.report.AllureAttachmentManager;
import utils.report.AllureConstants;
import utils.report.AllureEnvironmentManager;
import utils.report.AllureReportGenerator;
import utils.validations.Validation;

import java.io.File;
import java.io.IOException;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {
    @Override
    public void onExecutionStart() {
        PropertyReader.loadProperties();
        LogsManager.info("Execution Started");
        cleanTestOutputDirectories();
        LogsManager.info("Test output directories cleaned successfully.");
        createTestOutputDirectories();
        LogsManager.info("Test output directories created successfully.");
        AllureEnvironmentManager.setEnvironmentVariables();
        LogsManager.info("Allure environment variables set successfully.");


    }

    @Override
    public void onExecutionFinish() {
        boolean fullReportGenerated = AllureReportGenerator.generateReports(false);
        if (fullReportGenerated) {
            LogsManager.info("Allure reports generated successfully.");
        }

        AllureReportGenerator.copyHistory();
        LogsManager.info("Allure history copied successfully.");

        boolean singleFileReportGenerated = AllureReportGenerator.generateReports(true);
        if (singleFileReportGenerated) {
            LogsManager.info("Allure single file report generated successfully.");
            AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        }

    }

    @Override
    public void beforeInvocation(org.testng.IInvokedMethod method, org.testng.ITestResult testResult) {
        if (method.isTestMethod()) {
            ScreenRecordManager.startRecording();
        }
    }

    @Override
    public void afterInvocation(org.testng.IInvokedMethod method, org.testng.ITestResult testResult) {
        if (method.isTestMethod()) {
            ScreenRecordManager.stopRecording(testResult.getName());
            Validation.assertAll(testResult);
            if (testResult.getInstance() instanceof WebDriverProvider webDriverProvider) {
                var driver = webDriverProvider.getWebDriver();
                switch (testResult.getStatus()) {
                    case org.testng.ITestResult.SUCCESS ->
                            ScreenshotManager.takeScreenshot(driver, "passed-" + testResult.getName());
                    case org.testng.ITestResult.FAILURE ->
                            ScreenshotManager.takeScreenshot(driver, "failed-" + testResult.getName());
                    case org.testng.ITestResult.SKIP ->
                            ScreenshotManager.takeScreenshot(driver, "skipped-" + testResult.getName());
                }

                AllureAttachmentManager.attachLogs();
                AllureAttachmentManager.attachRecords(testResult.getName());


            }
        }
    }

    @Override
    public void onTestSuccess(org.testng.ITestResult result) {
        LogsManager.info("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(org.testng.ITestResult result) {
        LogsManager.error("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(org.testng.ITestResult result) {
        LogsManager.warn("Test Skipped: " + result.getName());
    }


    // cleaning and creating dirs(logs , screenshots, recordings, allure-results)

    private void cleanTestOutputDirectories() {
        FileManager.delDir(AllureConstants.RESULTS_FOLDER.toFile());
        FileManager.delDir(new File(ScreenshotManager.SCREENSHOT_DIR));
        FileManager.delDir(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileManager.forceDelDir(new File(LogsManager.LOGS_PATH + File.separator + "logs.log"));
    }

    private void createTestOutputDirectories() {
        FileManager.createDir(ScreenshotManager.SCREENSHOT_DIR);
        FileManager.createDir(ScreenRecordManager.RECORDINGS_PATH);
    }


}
