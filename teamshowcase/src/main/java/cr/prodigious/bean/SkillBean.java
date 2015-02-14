package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Define a skill category with all the subcategories inside
 * It works as a catalog for the user, so that the user can select the his/her own skills.
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "category",
        "subcategories"
})

public class SkillBean implements Serializable {

    @JsonProperty("category")
    private SkillCategoryBean skillCategoryBean;


    @JsonProperty("subcategories")
    private List<SkillSubCategoryBean> subcategories = null;

    @JsonProperty("category")
    public SkillCategoryBean getSkillCategoryBean() {
        return skillCategoryBean;
    }

    @JsonProperty("category")
    public void setSkillCategoryBean(SkillCategoryBean skillCategoryBean) {
        this.skillCategoryBean = skillCategoryBean;
    }

    @JsonProperty("subcategories")
    public List<SkillSubCategoryBean> getSubcategories() {
        return subcategories;
    }

    @JsonProperty("subcategories")
    public void setSubcategories(List<SkillSubCategoryBean> subcategories) {
        this.subcategories = subcategories;
    }
} // E:O:F:SkillBean.
