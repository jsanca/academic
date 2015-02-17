package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Basically a wrapper of User Session Bean and Boolean for the result
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result",
        "userSessionBean"
})
public class LoginResult implements Serializable {

    @JsonProperty("result")
    private BooleanMessage result = null;

    @JsonProperty("userSessionBean")
    private UserSessionBean userSessionBean = null;

    @JsonProperty("result")
    public BooleanMessage getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(BooleanMessage result) {
        this.result = result;
    }

    @JsonProperty("userSessionBean")
    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    @JsonProperty("userSessionBean")
    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }
}  // E:O:F:LoginResult.
