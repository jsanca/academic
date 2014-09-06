package monentrevue.service.impl;

import monentrevue.bean.User;
import monentrevue.service.UserService;

/**
 * Dummy implementation using a fixed user and pass (admin:admin)
 *
 * Date: 9/3/14
 * Time: 11:43 PM
 * @author jsanca
 */
public class DummyFixedUserServiceImpl implements UserService {

    private User defaultUser = new User();

    public DummyFixedUserServiceImpl() {

        this.defaultUser.setName("admin");
        this.defaultUser.setPassword("admin");
    }

    /**
     * Get the user (if it exists)
     *
     * @param userName String
     * @return User
     */
    @Override
    public User getUser(String userName) {

        return ("admin".equals(userName))?
                this.defaultUser:null;
    }
} // E:O:F:DummyFixedUserServiceImpl.
