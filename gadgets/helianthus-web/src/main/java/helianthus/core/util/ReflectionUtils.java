package helianthus.core.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reflection Utils
 * <p/>
 * Date: 5/12/14
 * Time: 8:13 PM
 *
 * @author jsanca
 */
public class ReflectionUtils implements Serializable {

    private static Logger logger =
            Logger.getLogger(ReflectionUtils.class.getName());

    public static ReflectionUtils INSTANCE =
            new ReflectionUtils();

    /**
     * Create a new instance of clazz
     *
     * @param clazz
     * @return T
     */
    public Object createInstance(final String clazz) {

        Class aClass = null;
        Object instance = null;

        try {

            aClass = Class.forName(clazz);
            instance = this.createInstance(aClass);
        } catch (ClassNotFoundException e) {

            logger.log(Level.INFO,
                    e.getMessage(), e);
        }

        return instance;
    } // createInstance.

    /**
     * Create a new instance of clazz
     *
     * @param clazz
     * @param <T>
     * @return T
     */
    public <T> T createInstance(Class<T> clazz) {

        T t = null;

        try {

            t = clazz.newInstance();
        } catch (Exception e) {

            logger.log(Level.INFO,
                    e.getMessage(), e);
        }

        return t;
    } // createInstance.
} // E:O:F:ReflectionUtils.
