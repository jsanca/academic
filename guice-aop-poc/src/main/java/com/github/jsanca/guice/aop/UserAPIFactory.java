package com.github.jsanca.guice.aop;


import com.google.inject.Provider;

import javax.inject.Inject;

public class UserAPIFactory implements Provider<UserAPI> {

    private final UserDAO userDAO;

    @Inject
    public UserAPIFactory(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserAPI get() {

        return new MyUserAPI(this.userDAO, new UserUtil());
    }

    private final class MyUserAPI implements UserAPI {

        private final UserDAO userDAO;
        private final UserUtil userUtil;

        protected MyUserAPI (final UserDAO userDAO,
                             final UserUtil userUtil) {

            this.userDAO  = userDAO;
            this.userUtil = userUtil;
        }

        @Override
        public User findUserByName(final String name) {
            System.out.println("MyUserAPI.findUserByName");
            return this.userDAO.getUserByName(name);
        }

        @Transactional
        @Override
        public void saveUser (final User user) {
            System.out.println("MyUserAPI.saveUser");
            this.userDAO.save (user);
        }

        @Override
        public User getDefaultUser() {
            System.out.println("MyUserAPI.getDefaultUser");
            return this.userUtil.getDefaultUser();
        }
    }
}
