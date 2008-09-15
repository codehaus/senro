package org.senro.utils;

import java.lang.reflect.Field;
import java.text.MessageFormat;

public class ClassUtils {
    /**
     * Tries to find a field by its name. The search will be executed up the hierarchy until it reach the Object class.
     *
     * @param clazz Class to start with
     * @param name  Name of the field
     * @return Field
     * @throws RuntimeException when a field could not be found
     */
    public static Field getField(Class clazz, String name) throws RuntimeException {
        Field field = null;
        try {
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            // do nothing here
        }
        if (field != null) return field;
        if (!clazz.equals(Object.class)) {
            return getField(clazz.getSuperclass(), name);
        }
        throw new RuntimeException(new NoSuchFieldException(MessageFormat.format("Field {0} was not found.", name)));
    }

}
