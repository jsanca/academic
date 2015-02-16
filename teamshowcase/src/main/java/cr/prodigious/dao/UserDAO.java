package cr.prodigious.dao;

import cr.prodigious.bean.ManagerBean;
import cr.prodigious.bean.UserBean;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the functionality for User DAO
 * @author jsanca
 */
public interface UserDAO extends Serializable {

    /**
     * Creates (Stored) an user bean into the database
     * @param userBean
     * @return boolean true if it is successfully added
     */
    public boolean create(UserBean userBean);

    /**
     * Find by Name
     * @param userName
     * @return UserBean
     */
    public UserBean findByUserName (String userName);


    /**
     * Find by email
     * @param email
     * @return UserBean
     */
    public UserBean findByEmail (String email);

} // E:O:F:UserDAO.
