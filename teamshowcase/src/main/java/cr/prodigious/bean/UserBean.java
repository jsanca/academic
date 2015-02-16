package cr.prodigious.bean;

import java.io.Serializable;

/**
 * Application User
 *
 * @author jsanca
 */
public class UserBean implements Serializable {

    private String id   = null;


    private String email = null;

    // password will be storaged in md5 in keep the user security
    private String password = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
} // E:O:F:UserBean.
