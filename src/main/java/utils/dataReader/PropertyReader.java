package utils.dataReader;

import org.apache.commons.io.FileUtils;
import utils.logs.LogsManager;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

public  class PropertyReader {

    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFiles;
            propertiesFiles = FileUtils.listFiles(new File("src/main/resources"), new String[]{"properties"}, true);
            propertiesFiles.forEach(file -> {
                try {
                    properties.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                    LogsManager.error("Error loading properties file: " + file.getPath());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            return properties;

        } catch (Exception e) {
            LogsManager.error("Error loading properties files");
            return null;
        }

    }


    public static String getProperty(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            LogsManager.error("Error getting property: " + key);
            return "";
        }

    }

}
