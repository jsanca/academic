package monentrevue.bean;

import java.io.Serializable;

/**
 * interviewer info
 * Date: 8/21/14
 * Time: 8:42 PM
 * @author jsanca
 */
public class Interviewer implements Serializable {

    private String name;

    private String email;

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
} // E:O:F:Interviewed.
