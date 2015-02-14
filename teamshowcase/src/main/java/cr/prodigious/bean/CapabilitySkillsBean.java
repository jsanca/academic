package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Encapsulate the Capability skills tree
 *
 *
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "order",
        "skills"
})
public class CapabilitySkillsBean implements Serializable {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("order")
    private Integer order = null;

    @JsonProperty("skills")
    private List<SkillBean> skillBeans = null;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("order")
    public Integer getOrder() {
        return order;
    }

    @JsonProperty("order")
    public void setOrder(Integer order) {
        this.order = order;
    }

    @JsonProperty("skills")
    public List<SkillBean> getSkillBeans() {
        return skillBeans;
    }

    @JsonProperty("skills")
    public void setSkillBeans(List<SkillBean> skillBeans) {
        this.skillBeans = skillBeans;
    }
} // E:O:F:CapabilitySkillsBean.
