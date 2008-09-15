package ro.siveco.svapnt.common.entity.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistentToManyGetter {
    String relName();
}
