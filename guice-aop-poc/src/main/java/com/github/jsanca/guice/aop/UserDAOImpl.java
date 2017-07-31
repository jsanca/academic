package com.github.jsanca.guice.aop;

import javax.inject.Singleton;

@Singleton
public class UserDAOImpl implements UserDAO {


    @Cacheable (key = "userkey{0}", group = "userDAO")
    @Override
    public User getUserByName(final String name) {
        return new User(name, "dummy user");
    }

    @Purge (key = "userkey{0.name}", group = "userDAO")
    @Override
    public void save(final User user) {

        System.out.println("Saving the user: " + user.getName());
    }
}
