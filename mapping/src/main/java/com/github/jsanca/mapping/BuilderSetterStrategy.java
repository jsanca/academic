package com.github.jsanca.mapping;

import org.apache.commons.beanutils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This strategy is used for a normal java bean, the pojo will have set's and get's
 * Applies if it has an empty constructor.
 * @author jsanca
 */
public class BuilderSetterStrategy implements CreatorAndSetterStrategy {

    public static final String BUILDER_METHOD = "build";
    private final Map<Class,  Class> classBuilderMap =
            new ConcurrentHashMap<>();

    @Override
    public <T> T createAndSet(final Class<T> classToUse,
                              final Map<String, Object> properties) {

        T instance     = null;
        Object builder = null;
        final Class<?> builderClass               =
                this.getBuilderClass(classToUse);

        if (null != builderClass) {

            try {

                builder  = builderClass.newInstance();
                for (final String propertyName : properties.keySet()) {

                    this.setProperty (builder, propertyName, properties.get(propertyName));
                }

                instance = this.build (builder, classToUse);
            } catch (Exception e) {
                e.printStackTrace();
                // todo: handle it
            }
        } else {
            // todo: throw error not any builder set.
        }

        return instance;
    }

    private <T> T build(final Object builder,
                        final Class<T> classToUse) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        return (T) MethodUtils.invokeMethod(builder, BUILDER_METHOD, null);
    }

    private void setProperty(final Object builder,
                             final String propertyName,
                             final Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        MethodUtils.invokeMethod(builder, propertyName, value);
    }

    @Override
    public <T> boolean canApply (final Class<T> classToUse) {

        return (null != this.getBuilderClass(classToUse));
    }

    private <T>  Class<?>  getBuilderClass(final Class<T> classToUse) {

        Class<?> buildClass = null;

        if (!this.classBuilderMap.containsKey(classToUse)) {

            final Mapping mapping = AnnotationUtils
                    .getBeanAnnotation(classToUse, Mapping.class);

            if (null != mapping) {

                buildClass = mapping.builder();
            }

            // we set even a null to avoid check twice
            this.classBuilderMap.put(classToUse, buildClass);
        } else {

            buildClass =
                    this.classBuilderMap.get(classToUse);
        }

        return buildClass;
    }
}
