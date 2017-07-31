package com.github.jsanca.guice.aop;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("superUserAPI")
public class SuperUserAPI implements UserAPI {

    private final UserDAO userDAO;
    private final UserUtil userUtil;

    @Inject
    public SuperUserAPI(final UserDAO userDAO) {
        this (userDAO, new UserUtil());
    }

    public SuperUserAPI(final UserDAO userDAO, final UserUtil userUtil) {
        this.userDAO = userDAO;
        this.userUtil = userUtil;
    }

    @Override
    public User findUserByName(final String name) {
        System.out.println("SuperUserAPI.findUserByName");
        return this.userDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public void saveUser (final User user) {
        System.out.println("SuperUserAPI.saveUser");
        this.userDAO.save (user);
    }

    @Override
    public User getDefaultUser() {
        System.out.println("SuperUserAPI.getDefaultUser");
        return this.userUtil.getDefaultUser();
    }
}
