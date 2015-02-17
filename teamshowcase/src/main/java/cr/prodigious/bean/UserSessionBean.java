package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Session User; contains a clone of an UserBean with the password blank (for security reasons)
 * and the session token
 *
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sessionToken",
        "userBean"
})
public class UserSessionBean implements Serializable {

    @JsonProperty("sessionToken")
    private String sessionToken = null;

    @JsonProperty("userBean")
    private UserBean userBean = null;

    @JsonProperty("sessionToken")
    public String getSessionToken() {
        return sessionToken;
    }

    @JsonProperty("sessionToken")
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @JsonProperty("userBean")
    public UserBean getUserBean() {
        return userBean;
    }

    @JsonProperty("userBean")
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
} // E:O:F:UserBean.
