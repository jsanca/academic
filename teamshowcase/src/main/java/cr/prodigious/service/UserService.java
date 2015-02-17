package cr.prodigious.service;

import cr.prodigious.bean.UserBean;

import java.io.Serializable;

/**
 * User service for creating users and do login
 *
 * @author jsanca
 */
public interface UserService extends Serializable {

    /**
     * Create the user based on the email and password
     * @param email
     * @param password
     * @return UserBean get's the user with all the info related.
     */
    public UserBean create (String email, String password);

    /**
     * Find by user name
     * @param userId String
     * @return UserBean null if does not exists
     */
    public UserBean findByUserName(String userId);

    /**
     * Find by email
     * @param email String
     * @return UserBean null if does not exists
     */
    public UserBean findByEmail(String email);

    /**
     * Find the user by email and try to see if the login is ok
     * @param email String
     * @param password String
     * @return UserBean
     */
    public UserBean loginByEmail(String email, String password);
} // E:O:F:UserService.
