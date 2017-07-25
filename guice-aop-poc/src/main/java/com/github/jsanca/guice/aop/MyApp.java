package com.github.jsanca.guice.aop;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyApp {

    private final UserAPI userAPI;


    @Inject
    public MyApp(final UserAPI userAPI) {
        this.userAPI = userAPI;
    }

    public void sayHello () {

        System.out.println("Hello World!: " + userAPI.findUserByName("Tedy"));
        userAPI.saveUser(new User("Joe", "Bell"));
        System.out.println("Hello World!: " + userAPI.findUserByName("Joe"));
        System.out.println("Hello World!: " + userAPI.findUserByName("Tedy"));
    }


    public static void main( String[] args ) {

        final Injector injector = Guice.createInjector(new AppInjector());
        MyApp myApp = injector.getInstance(MyApp.class);

        myApp.sayHello();
    }
}
