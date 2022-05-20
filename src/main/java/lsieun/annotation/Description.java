package lsieun.annotation;

public @interface Description {
    Author author();

    Version version();

    String comments() default "";
}