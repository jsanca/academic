
package cr.prodigious.dto.cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CaseDTO implements Entity {

    private Long id;

    private String brand;
    private String description;
    private String image;
    private String inputText;
    private String excecutionTime;

    private List<ChallengeText> challengeText = new ArrayList<ChallengeText>();
    private List<WhatWeDoText> whatWeDoText = new ArrayList<WhatWeDoText>();
    private List<Object> whatWeDoList = new ArrayList<Object>();
    private List<Result> results = new ArrayList<Result>();
    private List<Chart> charts = new ArrayList<Chart>();
    private List<Keytool> keytools = new ArrayList<Keytool>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @param brand
     *     The brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     *     The inputText
     */
    public String getInputText() {
        return inputText;
    }

    /**
     *
     * @param inputText
     *     The inputText
     */
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }


    /*public List<ChallengeText> getChallengeText() {
        return challengeText;
    }

    public void setChallengeText(List<ChallengeText> challengeText) {
        this.challengeText = challengeText;
    }

    public List<WhatWeDoText> getWhatWeDoText() {
        return whatWeDoText;
    }

    public void setWhatWeDoText(List<WhatWeDoText> whatWeDoText) {
        this.whatWeDoText = whatWeDoText;
    }

    public List<Object> getWhatWeDoList() {
        return whatWeDoList;
    }

    public void setWhatWeDoList(List<Object> whatWeDoList) {
        this.whatWeDoList = whatWeDoList;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    } */

    public String getExcecutionTime() {
        return excecutionTime;
    }

    public void setExcecutionTime(String excecutionTime) {
        this.excecutionTime = excecutionTime;
    }

    /*public List<Chart> getCharts() {
        return charts;
    }

    public void setCharts(List<Chart> charts) {
        this.charts = charts;
    }

    public List<Keytool> getKeytools() {
        return keytools;
    }

    public void setKeytools(List<Keytool> keytools) {
        this.keytools = keytools;
    } */

    public Long Id() {

        return this.id;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(final Long id) {

        this.id = id;
    }
}
