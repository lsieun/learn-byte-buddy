package lsieun.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AuthorList.class)
public @interface Author {
    String firstname();

    String lastname() default "";
}
