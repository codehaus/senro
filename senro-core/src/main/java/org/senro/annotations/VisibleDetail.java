package org.senro.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that the field will be visible on GUI
 * when the entity is part of a toOne relation.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface VisibleDetail
{
	boolean value() default true;
}
