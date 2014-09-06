package monentrevue.bean;

import java.io.Serializable;

/**
 * User info
 *
 * Date: 9/3/14
 * Time: 11:38 PM
 * @author jsanca
 */
public class User implements Serializable {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
} // E:O:F:User.
