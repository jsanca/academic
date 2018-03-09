package com.github.jsanca.scheduler;

import static javafx.scene.input.KeyCode.T;

/**
 * Just an util for reflection
 * @author jsanca
 */
public class ReflectionUtils {

    public static  <T> T newInstance (final String className, final T defaultInstance) {

        T instance = defaultInstance;

        try {

            if (null != className) {
                instance = newInstance((Class<T>) Class.forName(className), defaultInstance);
            }
        } catch (ClassNotFoundException e) {

            instance = defaultInstance;
        }

        return instance;
    }

    public static <T> T newInstance (final Class<T> tClass, final T defaultInstance) {

        T instance = defaultInstance;

        try {

            instance = tClass.newInstance();
        } catch (Exception e) {

            instance = defaultInstance;
        }

        return instance;
    }
}
