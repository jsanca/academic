package cr.prodigious.dao;

import cr.prodigious.bean.CapabilityBean;
import cr.prodigious.bean.RegionBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the functionality for Capability positions DAO
 * @author jsanca
 */
public interface CapabilityPositionsDAO extends Serializable {

    /**
     * Update the list of capability positions
     * @param capabilityBeanList
     * @return boolean
     */
    public boolean update(List<CapabilityBean> capabilityBeanList);

    /**
     * Get the list of capability positions
     * @return List
     */
    public List<CapabilityBean>  get();

}
