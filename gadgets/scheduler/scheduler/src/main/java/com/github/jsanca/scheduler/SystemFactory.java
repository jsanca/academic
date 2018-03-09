package com.github.jsanca.scheduler;

import java.io.Serializable;
import java.util.Properties;

/**
 * Represents an user factory.
 * @author jsanca
 */
public class SystemFactory implements Serializable {

    private final Properties properties;
    private final UserInterface defaultUserInterface;

    private static class LazyHolder {
        private static final SystemFactory INSTANCE = new SystemFactory();
    }

    public static SystemFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    protected SystemFactory() {

        this.properties = new Properties();
        this.defaultUserInterface = new ConsoleUserInterface();
        this.init();
    }

    protected void init () {

        this.properties.putAll(System.getProperties());
    }

    public void init (final Properties properties) {

        this.properties.putAll(properties);
    }

    public UserInterface getUserInterface () {

        final String userInterfaceImplementation =
                this.properties.getProperty("userInterfaceImplementation");
        final UserInterface userInterface =
                ReflectionUtils.newInstance(userInterfaceImplementation, this.defaultUserInterface);

        return userInterface;
    }
} // E:O:F:SystemFactory.
