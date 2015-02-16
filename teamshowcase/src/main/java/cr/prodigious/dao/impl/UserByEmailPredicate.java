package cr.prodigious.dao.impl;

import cr.prodigious.bean.UserBean;
import cr.prodigious.dao.Predicate;

/**
 * Search by email criteria
 * @author jsanca
 */
public class UserByEmailPredicate implements Predicate<UserBean> {

    private final String email;

    /**
     * Constructor.
     * @param email String
     */
    public UserByEmailPredicate(final String email) {

        this.email = email;
    } // UserByEmailPredicate

    @Override
    public boolean match(final UserBean userBean) {

        return userBean.getEmail().equals(this.email);
    } // match.
} // E:O:F:UserByUserIdPredicate.
