
package cr.prodigious.bean.cases;

import java.util.HashMap;
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
    "skill",
    "porcentage"
})
public class Chart {

    @JsonProperty("skill")
    private String skill;
    @JsonProperty("porcentage")
    private String porcentage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The skill
     */
    @JsonProperty("skill")
    public String getSkill() {
        return skill;
    }

    /**
     * 
     * @param skill
     *     The skill
     */
    @JsonProperty("skill")
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * 
     * @return
     *     The porcentage
     */
    @JsonProperty("porcentage")
    public String getPorcentage() {
        return porcentage;
    }

    /**
     * 
     * @param porcentage
     *     The porcentage
     */
    @JsonProperty("porcentage")
    public void setPorcentage(String porcentage) {
        this.porcentage = porcentage;
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
