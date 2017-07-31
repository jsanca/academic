package com.github.jsanca.guice.aop;

public interface UserAPI {

    User findUserByName (String name);

    void saveUser (User user);

    User getDefaultUser ();
}
