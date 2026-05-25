package utils;

import org.apache.commons.io.FileUtils;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

import java.io.File;

public class FileManager {
    private static final String USER_DIR = PropertyReader.getProperty("user.dir");

    public FileManager() {
    } //TO prevent init

    //Renaming Dir
    public static void renameDir(String oldPath, String newPath) {
        try {
            File oldFile = new File(USER_DIR + oldPath);
            File newFile = new File(USER_DIR + newPath);
            if (oldFile.exists()) {
                if (oldFile.renameTo(newFile)) {
                    LogsManager.info("Directory renamed from: " + oldFile.getAbsolutePath() + " to: " + newFile.getAbsolutePath());
                } else {
                    LogsManager.error("Failed to rename Directory: " + oldFile.getAbsolutePath() + " to: " + newFile.getAbsolutePath());
                }
            } else {
                LogsManager.error("Directory does not exist: " + oldFile.getAbsolutePath());
            }

        } catch (Exception e) {
            LogsManager.error("Failed to rename Directory: " + oldPath + " to: " + newPath + " Error: " + e.getMessage());
        }
    }


    //Creating Dir
    public static void createDir(String path) {
        try {
            File file = new File(USER_DIR + path);
            if (!file.exists()) {
                file.mkdirs();
                LogsManager.info("Directory created: " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            LogsManager.error("Failed to create Directory: " + path + " Error: " + e.getMessage());
        }
    }


    //Cleaning Dir
    public static void delDir(File file) {
        try {
            FileUtils.deleteQuietly(file);

        } catch (Exception e) {
            LogsManager.error("Failed to clean Directory: " + file.getAbsolutePath() + " Error: " + e.getMessage());
        }

    }

    //force Delete
    public static void forceDelDir(File file) {
        try {
            FileUtils.forceDelete(file);
            LogsManager.info("Directory deleted: " + file.getAbsolutePath());

        } catch (Exception e) {
            LogsManager.error("Failed to force clean Directory: " + file.getAbsolutePath() + " Error: " + e.getMessage());
        }

    }


    public static void copyDir(File source, File dest) {
        try {
            FileUtils.copyDirectory(source, dest);
            LogsManager.info("Directory copied from: " + source.getAbsolutePath() + " to: " + dest.getAbsolutePath());
        } catch (Exception e) {
            LogsManager.error("Failed to copy Directory: " + source.getAbsolutePath() + " to: " + dest.getAbsolutePath() + " Error: " + e.getMessage());
        }
    }

//check if the file downloaded
    public static boolean isFileDownloaded(String fileName) {
        File downloadDir = new File(USER_DIR + PropertyReader.getProperty("downloadPath"));
        File[] files = downloadDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    LogsManager.info("File found: " + file.getAbsolutePath());
                    return true;
                }
            }
        }
        LogsManager.error("File not found: " + fileName);
        return false;
    }

}
