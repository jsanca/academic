package cr.prodigious.workshop.servlet;

import java.io.Serializable;

/**
 * Encapsulate the Context of the App
 *
 * Date: 4/2/14
 * Time: 7:02 PM
 * @author jsanca
 */
public interface Context extends Serializable {

    /**
     * Get the value if exists, otherwise returns the default value and insert it in the context
     * @param key String
     * @param defaultValue T
     * @param <T>
     * @return T
     */
    public abstract <T> T get(String key, T defaultValue);


    /**
     * Get a service from the context.
     * @param key String
     * @param beanClass Class
     * @param <T>
     * @return T
     */
    public abstract <T> T get(String key, Class<T> beanClass);

    /**
     * Get a service from the context.
     * @param key String
     * @param beanClass Class
     * @param constructorParameters Objects
     * @param <T>
     * @return T
     */
    public abstract <T> T get(String key, Class<T> beanClass, Object... constructorParameters);

    /**
     * Get an object from the context
     * @param key String
     * @return Object
     */
    public abstract Object get(String key);

    /**
     * Put an object to the context.
     * @param key String
     * @param o Object
     */
    public abstract void put(String key, Object o);

} // E:O:F:Context.
