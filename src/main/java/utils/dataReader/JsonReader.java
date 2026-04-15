package utils.dataReader;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.logs.LogsManager;

import java.io.FileReader;

public class JsonReader {


    String jsonReader;
    String jsonFileName;
    private final String TEST_DATA_PATH = "src/test/resources/";

    public JsonReader(String jsonFileName_) {
        this.jsonFileName = jsonFileName_;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsManager.error("Error reading JSON file: " + TEST_DATA_PATH + jsonFileName + ".json");
            throw new RuntimeException("Failed to read JSON file: " + jsonFileName, e);
        }
    }


    public String getJsonData(String jsonPath)
    {
        try {
            String value = JsonPath.read(jsonReader, jsonPath).toString();
            LogsManager.info("Read JSON path: " + jsonPath + " from file: " + jsonFileName);
            return value;
        } catch (Exception e) {
            LogsManager.error("Error reading JSON path: " + jsonPath + " from file: " + jsonFileName);
            return "";
        }
    }
}
