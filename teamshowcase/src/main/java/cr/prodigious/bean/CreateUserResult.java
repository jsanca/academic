package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Result for create user.
 *
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result",
         "userSessionBean"
})
public class CreateUserResult implements Serializable {

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
} // E:O:F:CreateUserResult.
