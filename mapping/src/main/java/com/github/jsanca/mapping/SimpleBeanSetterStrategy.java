package com.github.jsanca.mapping;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * This is a default strategy for beans, assumes the java bean has a setProperty methods accessibles and
 * an empty constructor to be created.
 * @author jsanca
 */
public class SimpleBeanSetterStrategy implements CreatorAndSetterStrategy {

    @Override
    public <T> T createAndSet(final Class<T> classToUse,
                              final Map<String, Object> properties) {

        T instance = null;

        try {

            instance = classToUse.newInstance();
            BeanUtils.populate(instance, properties);
        } catch (Exception e) {
            e.printStackTrace();
            // todo: handle it
        }

        return instance;
    }

    @Override
    public <T> boolean canApply (final Class<T> classToUse) {

        // this will be the default strategy so will applies as a fallback for everyone.
        return true;
    }

}
