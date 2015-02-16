package cr.prodigious.dao.impl;

import cr.prodigious.bean.UserBean;
import cr.prodigious.dao.Predicate;

/**
 * Search by user name criteria
 * @author jsanca
 */
public class UserByUserNamePredicate implements Predicate<UserBean> {

    private final String userName;

    /**
     * Constructor.
     * @param userName String
     */
    public UserByUserNamePredicate(final String userName) {

        this.userName = userName;
    } // UserByUserIdPredicate

    @Override
    public boolean match(final UserBean userBean) {

        return userBean.getUserName().equals(this.userName);
    } // match.
} // E:O:F:UserByUserIdPredicate.
