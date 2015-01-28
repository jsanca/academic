package cr.prodigious.dao;

import cr.prodigious.bean.ManagerBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the functionality for Manager DAO
 * @author jsanca
 */
public interface ManagerDAO extends Serializable {

    /**
     * Update the list of managers
     * @param managerBeanList
     * @return boolean
     */
    public boolean update(List<ManagerBean> managerBeanList);

    /**
     * Get the list of manangers
     * @return
     */
    public List<ManagerBean>  get ();

}
