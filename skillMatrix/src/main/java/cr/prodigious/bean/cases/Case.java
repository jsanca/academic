
package cr.prodigious.bean.cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "brand",
    "description",
    "image",
    "inputText",
    "challengeText",
    "whatWeDoText",
    "whatWeDoList",
    "results",
    "excecutionTime",
    "charts",
    "keytools"
})
public class Case implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("brand")
    private String brand;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image")
    private String image;
    @JsonProperty("inputText")
    private String inputText;
    @JsonProperty("challengeText")
    private List<ChallengeText> challengeText = new ArrayList<ChallengeText>();
    @JsonProperty("whatWeDoText")
    private List<WhatWeDoText> whatWeDoText = new ArrayList<WhatWeDoText>();
    @JsonProperty("whatWeDoList")
    private List<Object> whatWeDoList = new ArrayList<Object>();
    @JsonProperty("results")
    private List<Result> results = new ArrayList<Result>();
    @JsonProperty("excecutionTime")
    private String excecutionTime;
    @JsonProperty("charts")
    private List<Chart> charts = new ArrayList<Chart>();
    @JsonProperty("keytools")
    private List<Keytool> keytools = new ArrayList<Keytool>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The brand
     */
    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    /**
     * 
     * @param brand
     *     The brand
     */
    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The inputText
     */
    @JsonProperty("inputText")
    public String getInputText() {
        return inputText;
    }

    /**
     * 
     * @param inputText
     *     The inputText
     */
    @JsonProperty("inputText")
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    /**
     * 
     * @return
     *     The challengeText
     */
    @JsonProperty("challengeText")
    public List<ChallengeText> getChallengeText() {
        return challengeText;
    }

    /**
     * 
     * @param challengeText
     *     The challengeText
     */
    @JsonProperty("challengeText")
    public void setChallengeText(List<ChallengeText> challengeText) {
        this.challengeText = challengeText;
    }

    /**
     * 
     * @return
     *     The whatWeDoText
     */
    @JsonProperty("whatWeDoText")
    public List<WhatWeDoText> getWhatWeDoText() {
        return whatWeDoText;
    }

    /**
     * 
     * @param whatWeDoText
     *     The whatWeDoText
     */
    @JsonProperty("whatWeDoText")
    public void setWhatWeDoText(List<WhatWeDoText> whatWeDoText) {
        this.whatWeDoText = whatWeDoText;
    }

    /**
     * 
     * @return
     *     The whatWeDoList
     */
    @JsonProperty("whatWeDoList")
    public List<Object> getWhatWeDoList() {
        return whatWeDoList;
    }

    /**
     * 
     * @param whatWeDoList
     *     The whatWeDoList
     */
    @JsonProperty("whatWeDoList")
    public void setWhatWeDoList(List<Object> whatWeDoList) {
        this.whatWeDoList = whatWeDoList;
    }

    /**
     * 
     * @return
     *     The results
     */
    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The excecutionTime
     */
    @JsonProperty("excecutionTime")
    public String getExcecutionTime() {
        return excecutionTime;
    }

    /**
     * 
     * @param excecutionTime
     *     The excecutionTime
     */
    @JsonProperty("excecutionTime")
    public void setExcecutionTime(String excecutionTime) {
        this.excecutionTime = excecutionTime;
    }

    /**
     * 
     * @return
     *     The charts
     */
    @JsonProperty("charts")
    public List<Chart> getCharts() {
        return charts;
    }

    /**
     * 
     * @param charts
     *     The charts
     */
    @JsonProperty("charts")
    public void setCharts(List<Chart> charts) {
        this.charts = charts;
    }

    /**
     * 
     * @return
     *     The keytools
     */
    @JsonProperty("keytools")
    public List<Keytool> getKeytools() {
        return keytools;
    }

    /**
     * 
     * @param keytools
     *     The keytools
     */
    @JsonProperty("keytools")
    public void setKeytools(List<Keytool> keytools) {
        this.keytools = keytools;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("id")
    public Long getId() {

        return this.id;
    }

    @JsonProperty("id")
    public void setId(final Long id) {

        this.id = id;
    }
}
