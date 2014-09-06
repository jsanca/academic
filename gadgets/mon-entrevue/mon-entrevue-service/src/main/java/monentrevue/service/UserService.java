package monentrevue.service;

import monentrevue.bean.User;

import java.io.Serializable;

/**
 *  User service
 *
 * Date: 9/3/14
 * Time: 11:36 PM
 * @author jsanca
 */
public interface UserService extends Serializable {

    /**
     * Get the user (if it exists)
     * @param userName String
     * @return User
     */
    public User getUser (String userName);

} // E:O:F:UserService.
