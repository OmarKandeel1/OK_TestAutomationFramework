package utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
            System.out.println("Error reading Json File!");
        }
    }


    public String getJsonData(String jsonPath)
    {
        try {
           return JsonPath.read(jsonReader , jsonPath);
        } catch (Exception e) {
            System.out.println("Error reading Json File!");
            return "";
        }
    }
}
