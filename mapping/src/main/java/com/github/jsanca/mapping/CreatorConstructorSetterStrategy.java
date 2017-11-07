package com.github.jsanca.mapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This implementation is based on a method annotated by Mapping and suppose to receives
 * all attributes by parameters.
 * Usually useful for immutables using a constructor.
 * Applies if the constructor is annotated by Mapping.
 * @author jsanca
 */
public class CreatorConstructorSetterStrategy implements CreatorAndSetterStrategy {

    private final Map<Class, Constructor> classConstructorMap =
            new ConcurrentHashMap<>();

    private final Map<Constructor, List<String>> constructorParameterListMap =
            new ConcurrentHashMap<>();

    @Override
    public <T> T createAndSet(final Class<T> classToUse,
                              final Map<String, Object> properties) {

        int i      = 0;
        T instance = null;
        List<String> constructorFieldOrder        = null;
        final Constructor<T> constructorAnnotated =
                this.getCreatorConstructor(classToUse);
        Object []    constructorParameters        = null;
        Object       value                        = null;


        if (null != constructorAnnotated) {

            constructorFieldOrder = this.getFieldsOrders (constructorAnnotated);
            constructorParameters = new Object[constructorFieldOrder.size()];

            for (String parameterName : constructorFieldOrder) {

                if (properties.containsKey(parameterName)) {

                    value = properties.get(parameterName);
                } else {
                    value = null; // todo: warn the property has not a value and going to set null.
                }

                constructorParameters[i++] = value;
            }

            try {

                instance = constructorAnnotated
                        .newInstance(constructorParameters);
            } catch (Exception e) {
                e.printStackTrace();
                // todo: handle it
            }
        } else {
            // todo: throw error not any constructor annotated.
        }

        return instance;
    }

    private <T> List<String> getFieldsOrders(final Constructor<T> constructorAnnotated) {

        if (!this.constructorParameterListMap.containsKey(constructorAnnotated)) {

            final List<String> constructorFieldOrder = new ArrayList<>(); // todo: make immutable.
            final Parameter[] parameters =
                    constructorAnnotated.getParameters();
            Mapping mapping = null;

            for (final Parameter parameter : parameters) {

                mapping = (Mapping) AnnotationUtils.getParameterAnnotation
                        (parameter, Mapping.class);
                constructorFieldOrder.add(
                        (null != mapping) ? mapping.name() : parameter.getName());
            }

            this.constructorParameterListMap.put(constructorAnnotated, constructorFieldOrder);
            return constructorFieldOrder;
        }

        return this.constructorParameterListMap.get(constructorAnnotated);
    }

    @Override
    public <T> boolean canApply (final Class<T> classToUse) {

        return (null != this.getCreatorConstructor(classToUse));
    }

    private <T> Constructor<T> getCreatorConstructor (final Class<T> classToUse) {

        Constructor<T> constructorAnnotated = null;

        if (!this.classConstructorMap.containsKey(classToUse)) {

            final Constructor<T>[] constructors =
                    this.getConstructors(classToUse);

            for (final Constructor<T> constructor: constructors) {

                if (AnnotationUtils.isBeanAnnotatedBy(constructors, Mapping.class)) {

                    constructorAnnotated = constructor;
                    this.classConstructorMap.put(classToUse, constructorAnnotated);
                    break;
                }
            }
        } else {

            constructorAnnotated =
                    this.classConstructorMap.get(classToUse);
        }

        return constructorAnnotated;
    }


    // todo: this might be an util
    private <T> Constructor<T>[] getConstructors (final Class<T> classToUse) {

        return (Constructor<T>[]) classToUse.getConstructors();
    }

}
