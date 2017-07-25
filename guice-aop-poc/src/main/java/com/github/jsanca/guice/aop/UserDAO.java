package com.github.jsanca.guice.aop;

public interface UserDAO {

    User getUserByName (String name);

    void save(User user);
}
