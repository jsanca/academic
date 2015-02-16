
package cr.prodigious.bean.cases;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cr.prodigious.entity.Entity;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "cases"
})
public class CasesBean implements Entity {

    @JsonProperty("cases")
    private List<Case> cases = new ArrayList<Case>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    private Long id;

    /**
     *
     * @return
     *     The cases
     */
    @JsonProperty("cases")
    public List<Case> getCases() {
        return cases;
    }

    /**
     * 
     * @param cases
     *     The cases
     */
    @JsonProperty("cases")
    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * Get the entity id
     *
     * @return Long
     */
    @JsonProperty("id")
    public Long getUserId() {

        return this.id;
    }

    @JsonProperty("id")
    public void setId(final Long id) {

        this.id = id;
    }
}
