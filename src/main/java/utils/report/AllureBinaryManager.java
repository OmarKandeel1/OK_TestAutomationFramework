package utils.report;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import utils.OSUtils;
import utils.TerminalUtils;
import utils.dataReader.PropertyReader;
import utils.logs.LogsManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AllureBinaryManager {



    //*********************************   Inner Class   *********************************\\
    private static class AllureBinaryManagerHolder {
        static final String VERSION = resolveVersion();

        private static String resolveVersion() {
            String configuredVersion = PropertyReader.getProperty("allure.commandline.version");
            if (configuredVersion != null && !configuredVersion.isBlank()) {
                return configuredVersion.trim();
            }

            try {
                String url = Jsoup.connect("https://github.com/allure-framework/allure2/releases/latest")
                        .followRedirects(true).execute().url().toString();
                return url.split("/tag/")[1];
            } catch (Exception e) {
                LogsManager.error("Error while fetching allure version: " + e.getMessage());
                throw new RuntimeException("Failed to fetch allure version", e);
            }
        }

    }
    //*************************************************************************************\\

    private static Path downloadZip(String version) {
        try {
            String url = AllureConstants.ALLURE_ZIP_BASE_URL
                    + version + "/allure-commandline-" + version + ".zip";

            Path zipFile = Paths.get(
                    AllureConstants.EXTRACTION_DIR.toString(),
                    "allure-" + version + ".zip"
            );

            if (!Files.exists(zipFile)) {
                Files.createDirectories(AllureConstants.EXTRACTION_DIR);

                try (BufferedInputStream in = new BufferedInputStream(new URI(url).toURL().openStream());
                     OutputStream out = Files.newOutputStream(zipFile)) {

                    in.transferTo(out);

                } catch (Exception e) {
                    LogsManager.error("Invalid URL for Allure download: " + e.getMessage());
                    Files.deleteIfExists(zipFile);
                }
            }

            return zipFile;

        } catch (Exception e) {
            LogsManager.error("Error downloading Allure zip file: " + e.getMessage());
            return Paths.get("");
        }
    }

    //Extract ZIP file for Allure
    private static boolean extractZip(Path zipPath) {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path filePath = Paths.get(AllureConstants.EXTRACTION_DIR.toString(), File.separator, entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zipInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            return true;
        } catch (Exception e) {
            LogsManager.error("Error extracting Allure zip file", e.getMessage());
            return false;
        }
    }

    public static boolean downloadAndExtract() {
        try {
            String version = AllureBinaryManagerHolder.VERSION;
            Path extractionDir = Paths.get(AllureConstants.EXTRACTION_DIR.toString(), "allure-" + version);


            if (Files.isRegularFile(getExecutable())) {
                LogsManager.info("Allure binaries already exist.");
                return true;
            }

            if (Files.exists(extractionDir)) {
                LogsManager.warn("Allure folder exists but executable is missing. Reinstalling: " + extractionDir);
                deleteExtractionDirectory(extractionDir);
            }

            // Give execute permissions to the binary if not on Windows
            if (!OSUtils.getCurrentOS().equals(OSUtils.OS.WINDOWS)) {
                TerminalUtils.executeTerminalCommand("chmod", "u+x", AllureConstants.USER_DIR.toString());
            }

            Path zipPath = downloadZip(version);
            if (!Files.isRegularFile(zipPath)) {
                LogsManager.error("Allure zip file was not downloaded: " + zipPath);
                return false;
            }

            if (!extractZip(zipPath)) {
                return false;
            }

            LogsManager.info("Allure binaries downloaded and extracted.");

            if (!Files.isRegularFile(getExecutable())) {
                LogsManager.error("Allure executable was not found after extraction: " + getExecutable());
                return false;
            }

            // Give execute permissions to the binary if not on Windows
            if (!OSUtils.getCurrentOS().equals(OSUtils.OS.WINDOWS)) {
                TerminalUtils.executeTerminalCommand("chmod", "u+x", getExecutable().toString());
            }

            // Clean up the zip file after extraction
            Files.deleteIfExists(zipPath);

            return true;

        } catch (Exception e) {
            LogsManager.error("Error downloading or extracting binaries " + e.getMessage());
            return false;
        }
    }

    private static void deleteExtractionDirectory(Path extractionDir) throws IOException {
        if (!extractionDir.startsWith(AllureConstants.EXTRACTION_DIR)) {
            throw new IOException("Refusing to delete path outside Allure extraction directory: " + extractionDir);
        }

        FileUtils.deleteDirectory(extractionDir.toFile());
    }

    public static Path getExecutable() {
        String version = AllureBinaryManagerHolder.VERSION;
        Path binaryPath = Paths.get(AllureConstants.EXTRACTION_DIR.toString(), "allure-" + version, "bin", "allure");
        return OSUtils.getCurrentOS() == OSUtils.OS.WINDOWS
                ? binaryPath.resolveSibling(binaryPath.getFileName() + ".bat")
                : binaryPath;
    }

}
