package org.senro.metadata.util;

import java.util.Date;

/**
 * Provide a way to obtain an instance of a class. Defaults are defined for most commons classes.
 * Author: Claudiu Dumitrescu
 */
public class Instance {

    public static final Integer INTEGER = 0;

    public static final Long LONG = 0l;

    public static final Boolean BOOLEAN = false;

    public static final String STRING = new String();

    public static final Class CLASS = Class.class;


    public static final Date DATE = new Date();
}
