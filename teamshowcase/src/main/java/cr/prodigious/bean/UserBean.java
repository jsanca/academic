package cr.prodigious.bean;

import cr.prodigious.entity.Entity;

import java.io.Serializable;

/**
 * Application User
 *
 * @author jsanca
 */
public class UserBean implements Entity, Serializable, Cloneable {

    private Long userId = null;

    private String userName = null;


    private String email = null;

    // password will be storaged in md5 in keep the user security
    private String password = null;

    public Long getUserId() {

        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
} // E:O:F:UserBean.
