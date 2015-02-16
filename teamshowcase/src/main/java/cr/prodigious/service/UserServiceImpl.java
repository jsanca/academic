package cr.prodigious.service;

import cr.prodigious.bean.UserBean;
import cr.prodigious.dao.UserDAO;
import cr.prodigious.helper.ConversionHelper;
import cr.prodigious.helper.StringEncoder;

/**
 * Default implementation
 * @author jsanca
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private StringEncoder stringEncoder;

    private ConversionHelper conversionHelper;

    public void setUserDAO(UserDAO userDAO) {

        this.userDAO = userDAO;
    }

    public void setStringEncoder(StringEncoder stringEncoder) {

        this.stringEncoder = stringEncoder;
    }

    public void setConversionHelper(ConversionHelper conversionHelper) {

        this.conversionHelper = conversionHelper;
    }

    /**
     * Create the user based on the email and password
     *
     * @param email
     * @param password
     * @return UserBean get's the user with all the info related.
     */
    @Override
    public UserBean create(final String email, final String password) {

        final UserBean userBean =
                new UserBean();

        // todo: as soon as you get the third party backend
        // connect to the user directory info
        userBean.setUserName("unknown");
        userBean.setUserId
                (this.conversionHelper.toLongNonNumeric(email));
        userBean.setEmail(email);
        userBean.setPassword
                (this.stringEncoder.encode(password));


        return userBean;
    } // create.

    /**
     * Find by id
     *
     * @param userId String
     * @return UserBean null if does not exists
     */
    @Override
    public UserBean findByUserName(String userId) {
        return null;
    }

    /**
     * Find by email
     *
     * @param email String
     * @return UserBean null if does not exists
     */
    @Override
    public UserBean findByEmail(String email) {
        return null;
    }
} // E:O:F:UserServiceImpl.
