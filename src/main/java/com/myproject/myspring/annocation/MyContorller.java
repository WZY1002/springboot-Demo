package com.myproject.myspring.annocation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
//运行时可用
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyContorller {

    String value() default "";
}
