package org.senro.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specified whether the field will be displayed by the ReportEngine
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface VisibleList
{
	boolean value() default true;
}
