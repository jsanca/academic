package com.github.jsanca.scheduler;

/**
 * A console implementation.
 * @author jsanca
 */
public class ConsoleUserInterface implements UserInterface {

    public void message(final String message) {

        System.out.print(message);
    }
} // E:O:F:ConsoleUserInterface.
