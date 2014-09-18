package monentrevue.service;

import monentrevue.bean.User;

import java.io.Serializable;

/**
 * Determine if the password is valid for an user.
 * It can use an algorithm in order to apply to the passwordInput
 * @author jsanca
 */
public interface PasswordComparatorStrategy extends Serializable {


    public abstract boolean compare (User user, String passwordInput);

} // PasswordComparatorStrategy.
