
package cr.prodigious.bean.team;

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
    "categoryName",
    "categoryOps"
})
public class Category {

    @JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("categoryOps")
    private List<CategoryOp> categoryOps = new ArrayList<CategoryOp>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The categoryName
     */
    @JsonProperty("categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 
     * @param categoryName
     *     The categoryName
     */
    @JsonProperty("categoryName")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 
     * @return
     *     The categoryOps
     */
    @JsonProperty("categoryOps")
    public List<CategoryOp> getCategoryOps() {
        return categoryOps;
    }

    /**
     * 
     * @param categoryOps
     *     The categoryOps
     */
    @JsonProperty("categoryOps")
    public void setCategoryOps(List<CategoryOp> categoryOps) {
        this.categoryOps = categoryOps;
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
