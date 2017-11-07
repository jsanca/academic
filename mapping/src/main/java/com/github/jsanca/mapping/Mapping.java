package com.github.jsanca.mapping;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Mapping {

    String name()       default "";
    String column()     default "";
    String dateFormat() default "";
    Class<FieldMapper>  fieldMapper() default FieldMapper.class;
    Class<?>            builder()     default Void.class;
}
