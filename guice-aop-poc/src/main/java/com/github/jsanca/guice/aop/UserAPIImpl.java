package com.github.jsanca.guice.aop;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserAPIImpl implements UserAPI {

    private final UserDAO userDAO;

    @Inject
    public UserAPIImpl(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findUserByName(final String name) {
        return this.userDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public void saveUser (final User user) {

        this.userDAO.save (user);
    }
}
