package cr.prodigious.dao;

import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.SkillCategoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the functionality for Skill Category DAO
 *
 * it only returns a single list of a skills
 *
 * @author jsanca
 */
public interface SkillCategoryDAO extends Serializable {

    /**
     * Update the list of skills
     * @param skillCategoryBeans
     * @return boolean
     */
    public boolean update(List<SkillCategoryBean> skillCategoryBeans);

    /**
     * Get the list of skills
     * @return
     */
    public List<SkillCategoryBean>  get();

}
