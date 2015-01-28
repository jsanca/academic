package cr.prodigious.dao;

import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.RegionBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the functionality for Region DAO
 * @author jsanca
 */
public interface RegionDAO extends Serializable {

    /**
     * Update the list of regions
     * @param regionBeanList
     * @return boolean
     */
    public boolean update(List<RegionBean> regionBeanList);

    /**
     * Get the list of regions
     * @return List
     */
    public List<RegionBean>  get();

}
