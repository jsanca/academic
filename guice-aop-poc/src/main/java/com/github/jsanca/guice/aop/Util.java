package com.github.jsanca.guice.aop;

import javax.inject.Inject;

/**
 * Created by jsanca on 8/1/17.
 */
public class Util {

    @Inject
    PrintHelper printHelper;

    void printSomething () {
        printHelper.print("The util");
    }
}
