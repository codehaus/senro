package org.senro.rules;

import java.util.ArrayList;
import java.util.Collection;


public class ThreadLocalContext {
    private static ThreadLocal threadLocal = new ThreadLocal();


    /**
     * @return A collection with all the objects from the context.
     */
    public static Collection get() {
        return (Collection) threadLocal.get();
    }

    /**
     * Add an object to the context.
     *
     * @param anObject Object to add to current context.
     */
    public static void put(Object anObject) {
        Collection context = (Collection) threadLocal.get();
        if (context == null) {
            context = new ArrayList();
        }
        context.add(anObject);
        threadLocal.set(context);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
