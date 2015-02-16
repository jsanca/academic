package cr.prodigious.bean;

import java.io.Serializable;

/**
 * Session User; contains a clone of an UserBean with the password blank (for security reasons)
 * and the session token
 *
 * @author jsanca
 */
public class UserSessionBean implements Serializable {

    private String sessionToken = null;

    private UserBean userBean = null;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
} // E:O:F:UserBean.
