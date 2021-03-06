package com.nuclearthinking.game.obj.jsonpojo;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 24.12.2015
 * Time: 16:18
 *
 * @author Vladislav Radchenko (onfient@gmail.com)
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "inputNameMessage"
})
public class Messages {

    @JsonProperty("inputNameMessage")
    private String inputNameMessage;

    @JsonProperty("welcomeMessage")
    private String welcomeMessage;

    @JsonProperty("inputText")
    private String inputText;

    @JsonProperty("unacceptableInputTooLong")
    private String unacceptableInputTooLong;

    @JsonProperty("unacceptableInputTooManyWhiteSpaces")
    private String unacceptableInputTooManyWhiteSpaces;

    @JsonProperty("firstCharNumeric")
    private String firstCharNumeric;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getFirstCharNumeric() {
        return firstCharNumeric;
    }

    public String getUnacceptableInputTooManyWhiteSpaces() {
        return unacceptableInputTooManyWhiteSpaces;
    }

    public String getUnacceptableInputTooLong() {
        return unacceptableInputTooLong;
    }

    public String getInputText() {
        return inputText;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @JsonProperty("inputNameMessage")
    public String getInputNameMessage() {
        return inputNameMessage;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
