package utils.report;

import org.jsoup.Jsoup;
import utils.OSUtils;
import utils.TerminalUtils;
import utils.logs.LogsManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AllureBinaryManager {



    //*********************************   Inner Class   *********************************\\
    private static class AllureBinaryManagerHolder {
        static final String VERSION = resloveVersion();

        private static String resloveVersion() {
            try{
                String url = Jsoup.connect("https://github.com/allure-framework/allure2/releases/latest")
                        .followRedirects(true).execute().url().toString();
                return url.split("/tag/")[1];
            }catch (Exception e)
            {
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
                }
            }

            return zipFile;

        } catch (Exception e) {
            LogsManager.error("Error downloading Allure zip file: " + e.getMessage());
            return Paths.get("");
        }
    }

    //Extract ZIP file for Allure
    private static void extractZip(Path zipPath) {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path filePath = Paths.get(AllureConstants.EXTRACTION_DIR.toString(), File.separator, entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zipInputStream, filePath);
                }
            }
        } catch (Exception e) {
            LogsManager.error("Error extracting Allure zip file", e.getMessage());
        }
    }

    public static void downloadAndExtract() {
        try {
            String version = AllureBinaryManagerHolder.VERSION;
            Path extractionDir = Paths.get(AllureConstants.EXTRACTION_DIR.toString(), "allure-" + version);


            if (Files.exists(extractionDir)) {
                LogsManager.info("Allure binaries already exist.");
                return;
            }

            // Give execute permissions to the binary if not on Windows
            if (!OSUtils.getCurrentOS().equals(OSUtils.OS.WINDOWS)) {
                TerminalUtils.executeTerminalCommand("chmod", "u+x", AllureConstants.USER_DIR.toString());
            }

            Path zipPath = downloadZip(version);
            extractZip(zipPath);

            LogsManager.info("Allure binaries downloaded and extracted.");

            // Give execute permissions to the binary if not on Windows
            if (!OSUtils.getCurrentOS().equals(OSUtils.OS.WINDOWS)) {
                TerminalUtils.executeTerminalCommand("chmod", "u+x", getExecutable().toString());
            }

            // Clean up the zip file after extraction
            Files.deleteIfExists(
                    Files.list(AllureConstants.EXTRACTION_DIR)
                            .filter((Path p) -> p.toString().endsWith(".zip"))
                            .findFirst()
                            .orElseThrow()
            );

        } catch (Exception e) {
            LogsManager.error("Error downloading or extracting binaries " + e.getMessage());
        }
    }

    public static Path getExecutable() {
        String version = AllureBinaryManagerHolder.VERSION;
        Path binaryPath = Paths.get(AllureConstants.EXTRACTION_DIR.toString(), "allure-" + version, "bin", "allure");
        return OSUtils.getCurrentOS() == OSUtils.OS.WINDOWS
                ? binaryPath.resolveSibling(binaryPath.getFileName() + ".bat")
                : binaryPath;
    }

}
