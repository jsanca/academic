package com.github.jsanca.guice.aop;


import com.google.inject.Provider;

import javax.inject.Inject;

public class UserAPIProvider implements Provider<UserAPI> {

    private final UserDAO userDAO;

    @Inject
    public UserAPIProvider(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserAPI get() {

        return new UserAPIFactory(this.userDAO).getUserApi();
    }

}
