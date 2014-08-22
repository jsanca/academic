package monentrevue.bean;

import java.io.Serializable;

/**
 * interviewed info
 * Date: 8/21/14
 * Time: 8:42 PM
 * @author jsanca
 */
public class Interviewed implements Serializable {

    private String name;

    private String email;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
} // E:O:F:Interviewed.
