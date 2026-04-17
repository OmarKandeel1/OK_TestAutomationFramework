package utils.report;

import utils.dataReader.PropertyReader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AllureConstants {

    public static final Path USER_DIR = Paths.get(System.getProperty("user.dir"));
    public static final Path USER_HOME = Paths.get(System.getProperty("user.home"));

    public static final Path RESULTS_FOLDER = USER_DIR.resolve(
            PropertyReader.getProperty("allure.results.directory").trim()
    );

    public static final Path REPORT_PATH = USER_DIR.resolve(
            PropertyReader.getProperty("allure.report.directory").trim()
    );

    public static final Path FULL_REPORT_PATH = USER_DIR.resolve(
            PropertyReader.getProperty("allure.full.report.directory").trim()
    );

    public static final String HISTORY_FOLDER_NAME =
            PropertyReader.getProperty("allure.history.folder").trim();

    public static final Path HISTORY_FOLDER =
            FULL_REPORT_PATH.resolve(HISTORY_FOLDER_NAME);

    public static final Path RESULTS_HISTORY_FOLDER =
            RESULTS_FOLDER.resolve(HISTORY_FOLDER_NAME);

    public static final String INDEX_HTML =
            PropertyReader.getProperty("allure.index.file").trim();

    public static final String REPORT_PREFIX =
            PropertyReader.getProperty("allure.report.prefix").trim();

    public static final String REPORT_EXTENSION =
            PropertyReader.getProperty("allure.report.extension").trim();

    public static final String ALLURE_ZIP_BASE_URL =
            PropertyReader.getProperty("allure.zip.base.url").trim();

    public static final Path EXTRACTION_DIR = USER_HOME.resolve(
            PropertyReader.getProperty("allure.extraction.directory").trim()
    );
}