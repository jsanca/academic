
package cr.prodigious.bean.work;

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
import cr.prodigious.entity.Entity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "work"
})
public class WorkBean implements Entity {

    @JsonProperty("work")
    private List<Work> work = new ArrayList<Work>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    private Long id;

    /**
     * 
     * @return
     *     The work
     */
    @JsonProperty("work")
    public List<Work> getWork() {
        return work;
    }

    /**
     * 
     * @param work
     *     The work
     */
    @JsonProperty("work")
    public void setWork(List<Work> work) {
        this.work = work;
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
    public Long getUserId() {

        return this.id;
    }

    @JsonProperty("id")
    public void setId(final Long id) {

        this.id = id;
    }
}
