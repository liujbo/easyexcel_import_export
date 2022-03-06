package com.example.demo.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DropDownSetField {

    // 固定下拉内容
    String[] source() default {};

    // 动态下拉内容
    Class[] sourceClass() default {};

}
