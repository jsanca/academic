package com.github.jsanca.guice.aop;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyApp {

    private final UserAPI userAPI;

    private static final Injector injector =
            Guice.createInjector(new PersistenceModule(),
                    new ApiModule());

    public static Injector getInjector() {
        return injector;
    }

    @Inject @CustomUserAPI
    private  UserAPI userAPI2;

    @Inject
    private PrintHelper printHelper;

    @Inject
    public MyApp(final UserAPI userAPI) {
        this.userAPI = userAPI;
    }

    /*@Inject
    public MyApp(@Named("superUserAPI") final UserAPI userAPI) {
        this.userAPI = userAPI;
    }
    */

    /*@Inject
    public MyApp(@Named("myUserAPI") final UserAPI userAPI) {
        this.userAPI = userAPI;
    }
    */

    public void sayHello () {

        printHelper.print("Hello World1!: " + userAPI.findUserByName("Tedy"));
        userAPI.saveUser(new User("Joe", "Bell"));
        printHelper.print("Hello World2!: " + userAPI.findUserByName("Joe"));
        printHelper.print("Hello World3!: " + userAPI.findUserByName("Tedy"));
        printHelper.print("Hello World4!: " + userAPI.getDefaultUser());

        printHelper.print("*******************************\n");

        printHelper.print("Hello World1!: " + userAPI2.findUserByName("Tedy"));
        userAPI.saveUser(new User("Joe", "Bell"));
        printHelper.print("Hello World2!: " + userAPI2.findUserByName("Joe"));
        printHelper.print("Hello World3!: " + userAPI2.findUserByName("Tedy"));
        printHelper.print("Hello World4!: " + userAPI2.getDefaultUser());
    }


    public static void main( String[] args ) {

        MyApp myApp = getInjector().getInstance(MyApp.class);

        myApp.sayHello();

    }
}
