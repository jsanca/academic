package com.github.jsanca.guice.aop;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@CustomUserAPI
public class CustomUserAPIImpl implements UserAPI {

    private final UserDAO userDAO;
    private final UserUtil userUtil;

    @Inject
    public CustomUserAPIImpl(final UserDAO userDAO, final UserUtil userUtil) {
        this.userDAO = userDAO;
        this.userUtil = userUtil;
    }

    @Override
    public User findUserByName(final String name) {
        System.out.println("CustomUserAPIImpl.findUserByName");
        return this.userDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public void saveUser (final User user) {
        System.out.println("CustomUserAPIImpl.saveUser");
        this.userDAO.save (user);
    }

    @Override
    public User getDefaultUser() {
        System.out.println("CustomUserAPIImpl.getDefaultUser");
        return this.userUtil.getDefaultUser();
    }


}
