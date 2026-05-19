package pages.com.automationexercices.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponseModel {

    @JsonProperty("responseCode")
    public int responseCode;

    @JsonProperty("message")
    public String message;
}
