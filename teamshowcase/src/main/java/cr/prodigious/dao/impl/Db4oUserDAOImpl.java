package cr.prodigious.dao.impl;

import cr.prodigious.bean.UserBean;
import cr.prodigious.dao.Predicate;
import cr.prodigious.dao.base.BaseDAO;
import cr.prodigious.dao.UserDAO;
import cr.prodigious.entity.Entity;

import java.util.List;

/**
 * DB4O impl
 *
 * @author jsanca
 */
public class Db4oUserDAOImpl extends BaseDAO<UserBean> implements UserDAO {


    /**
     * Creates (Stored) an user bean into the database
     *
     * @param userBean
     * @return boolean true if it is successfully added
     */
    @Override
    public boolean create(final UserBean userBean) {

        return this.insertOrUpdate(userBean);
    } // create.

    /**
     * Find by Name
     *
     * @param userName
     * @return UserBean
     */
    @Override
    public UserBean findByUserName(final String userName) {

        UserBean userBean = null;
        List<UserBean> userBeanList = null;

        userBeanList =
                 this.getDataBase()
                    .selectByPredicate
                        (new UserByUserNamePredicate(userName));

        if ((null != userBeanList) &&
                (userBeanList.size() > 0)) {

            userBean =
                userBeanList.get(0);
        }

        return userBean;
    } // findByUserName.

    /**
     * Find by email
     *
     * @param email
     * @return UserBean
     */
    @Override
    public UserBean findByEmail(final String email) {

        UserBean userBean = null;
        List<UserBean> userBeanList = null;

        userBeanList =
                this.getDataBase()
                        .selectByPredicate
                                (new UserByEmailPredicate(email));

        if ((null != userBeanList) &&
                (userBeanList.size() > 0)) {

            userBean =
                    userBeanList.get(0);
        }

        return userBean;
    } // findByEmail.

    @Override
    protected int getDepth() {

        return 5;
    }

    @Override
    protected Class<UserBean> getEntityClass() {

        return UserBean.class;
    }
} // Db4oUserDAOImpl.
