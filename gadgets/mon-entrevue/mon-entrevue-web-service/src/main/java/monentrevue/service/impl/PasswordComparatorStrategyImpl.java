package monentrevue.service.impl;

import monentrevue.bean.User;
import monentrevue.service.PasswordComparatorStrategy;

/**
 * Just make an equals without any extra processing, crypto, etc.
 * @author jsanca
 */
public class PasswordComparatorStrategyImpl implements PasswordComparatorStrategy {

    @Override
    public boolean compare(final User user,
                           final String passwordInput) {

        return user.getPassword().equals(passwordInput);
    } // compare.
} // E:O:F:PasswordComparatorStrategyImpl.
