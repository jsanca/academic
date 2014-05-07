package cr.prodigious.workshop.servlet.wrapper;

import cr.prodigious.workshop.servlet.Context;

import javax.servlet.ServletContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Wrapper of the app context.
 * Date: 4/2/14
 * Time: 7:04 PM
 * @author jsanca
 */
public class ContextWrapper implements Context {

    private ServletContext servletContext = null;

    public ContextWrapper(final ServletContext servletContext) {

        this.servletContext = servletContext;
    }


    /**
     * Get the value if exists, otherwise returns the default value and insert it in the context
     *
     * @param key          String
     * @param defaultValue T
     * @param <T>
     * @return T
     */
    @Override
    public <T> T get(final String key, final T defaultValue) {

        T t = (T)this.get(key);

        if (null == t) {

            t = defaultValue;
            this.put(key, t);
        }

        return t;
    }

    /**
     * Get a service from the context.
     *
     * @param key       String
     * @param beanClass Class
     * @param <T>
     * @return T
     */
    @Override
    public <T> T get(final String key, final Class<T> beanClass) {

        T t = null;

        t = (T)this.get(key);

        if (null == t) {

            try {

                t = beanClass.newInstance();

                this.put(key, t);
            } catch (InstantiationException e) {

                // todo: do something?
            } catch (IllegalAccessException e) {

                // todo: do something?
            }
        }

        return t;
    }

    /**
     * Get a service from the context.
     *
     * @param key                   String
     * @param beanClass             Class
     * @param constructorParameters Objects
     * @param <T>
     * @return T
     */
    @Override
    public <T> T get(String key, Class<T> beanClass, Object... constructorParameters) {

        T t = null;
        final Constructor constructor;

        try {

            if (null != constructorParameters) {

                t = (T)this.get(key);

                if (null == t) {

                    constructor =
                        beanClass.getConstructor(
                                this.getConstructorParametersType(constructorParameters));

                    t = (T)constructor.newInstance(constructorParameters);

                    this.put(key, t);
                }
            } else {

                t = this.get(key, beanClass);
            }
        } catch (NoSuchMethodException e) {

            // todo: do something
        } catch (InvocationTargetException e) {

            // todo: do something
        } catch (InstantiationException e) {

            // todo: do something
        } catch (IllegalAccessException e) {

            // todo: do something
        }

        return t;
    }

    private Class<Object> [] getConstructorParametersType(final Object[] constructorParameters) {

        final int arraySize = constructorParameters.length;
        ArrayList<Class> constructorParametersTypeList =
                new ArrayList<Class>();

        for(int i = 0; i < constructorParameters.length; ++i) {

            constructorParametersTypeList.add
                    (constructorParameters[i].getClass());
        }

        return (Class<Object> [])
                constructorParametersTypeList
                        .toArray(new Class [] {});
    }

    /**
     * Get an object from the context
     *
     * @param key String
     * @return Object
     */
    @Override
    public Object get(final String key) {

        return this.servletContext.getAttribute(key);
    }

    /**
     * Put an object to the context.
     *
     * @param key String
     * @param o   Object
     */
    @Override
    public void put(final String key, final Object o) {

        this.servletContext.setAttribute(key, o);
    }
} // E:O:F:ContextWrapper.
