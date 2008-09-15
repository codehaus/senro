package org.senro.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Label used to display a field or entity
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Label {
	String value();
}
