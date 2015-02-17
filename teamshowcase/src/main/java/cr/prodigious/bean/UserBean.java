package cr.prodigious.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cr.prodigious.entity.Entity;

import java.io.Serializable;

/**
 * Application User
 *
 * @author jsanca
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "userName",
        "email",
        "password"
})
public class UserBean implements Entity, Serializable, Cloneable {

    @JsonProperty("userId")
    private Long userId = null;

    @JsonProperty("userName")
    private String userName = null;


    @JsonProperty("email")
    private String email = null;

    // password will be storaged in md5 in keep the user security
    @JsonProperty("password")
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
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
} // E:O:F:UserBean.
