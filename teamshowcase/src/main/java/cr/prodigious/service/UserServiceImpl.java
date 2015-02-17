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

        if (null != this.findByEmail(email)) {

            throw new UserCreateException
                    ("User can not be created: already exists.");
        }

        // todo: check if the user email is valid?

        // todo: as soon as you get the third party backend
        // connect to the user directory info
        userBean.setUserName("unknown");
        userBean.setUserId
                (this.conversionHelper.toLongNonNumeric(email));
        userBean.setEmail(email);
        userBean.setPassword  // todo: any password checking strategy?
                (this.stringEncoder.encode(password));

        if (!this.userDAO.create(userBean)) {

            throw new UserCreateException
                    ("User could not be created");
        }

        // todo: send an email to verified creation

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
    public UserBean findByEmail(final String email) {

        UserBean userBean = null;

        userBean =
                this.userDAO.findByEmail(email);

        return userBean;
    }

    /**
     * Find the user by email and try to see if the login is ok
     *
     * @param email    String
     * @param password String
     * @return UserBean
     */
    @Override
    public UserBean loginByEmail(final String email, final String password) {

        UserBean userBean = null;
        String passwordEncoded = null;

        userBean =
                this.findByEmail(email);

        if (null != userBean) {

            passwordEncoded =
                this.stringEncoder.encode(password);

            if (!userBean.getPassword().equals(passwordEncoded)) {

                throw new LoginUserException("User does not exists or password is not right");
            }
        } else {

            throw new LoginUserException("User does not exists or password is not right");
        }

        return userBean;
    }
} // E:O:F:UserServiceImpl.
