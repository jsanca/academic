package com.github.jsanca.mapping;

import java.util.Map;

/**
 * The Creator and Setter strategy basically is in charge of creates the object and set a map of properties.
 * @author jsanca
 */
public interface CreatorAndSetterStrategy {

    <T> T createAndSet (final Class<T> classToUse,
                        final Map<String, Object> properties);

    <T> boolean canApply (final Class<T> classToUse);
}
