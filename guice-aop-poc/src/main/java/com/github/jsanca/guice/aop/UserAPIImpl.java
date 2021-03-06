package com.github.jsanca.guice.aop;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserAPIImpl implements UserAPI {

    private final UserDAO userDAO;
    private final UserUtil userUtil;

    @Inject
    public UserAPIImpl(final UserDAO userDAO, final UserUtil userUtil) {
        this.userDAO = userDAO;
        this.userUtil = userUtil;
    }

    @Override
    public User findUserByName(final String name) {
        System.out.println("UserAPI.findUserByName");
        return this.userDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public void saveUser (final User user) {
        System.out.println("UserAPI.saveUser");
        this.userDAO.save (user);
    }

    @Override
    public User getDefaultUser() {
        System.out.println("UserAPI.getDefaultUser");
        return this.userUtil.getDefaultUser();
    }


}
