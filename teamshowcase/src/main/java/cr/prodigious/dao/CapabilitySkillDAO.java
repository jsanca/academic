package cr.prodigious.dao;

import cr.prodigious.bean.CapabilitySkillsBean;
import cr.prodigious.bean.SkillCategoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the functionality for Capability Skills  DAO
 *
 * it only returns a single list of a capability skills
 *
 * @author jsanca
 */
public interface CapabilitySkillDAO extends Serializable {

    /**
     * Update the list of capability skills
     * @param capabilitySkillsBeans
     * @return boolean
     */
    public boolean update(List<CapabilitySkillsBean> capabilitySkillsBeans);

    /**
     * Get the list of capability skills
     * @return  List
     */
    public List<CapabilitySkillsBean>  get();

}  // E:O:F:CapabilitySkillDAO.
