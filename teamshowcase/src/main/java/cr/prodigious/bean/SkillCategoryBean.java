package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Define a skill category
 * It works as a catalog for the user, so that the user can select the his/her own skills.
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "order"
})

public class SkillCategoryBean implements Serializable {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("order")
    private Integer order = null;


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


} // E:O:F:SkillCategoryBean.
