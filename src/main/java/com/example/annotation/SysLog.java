package com.example.annotation;

import java.lang.annotation.*;

/**
 * @Author song
 * @Description: 用于打印接口、方法执行时间
 * @Date 2022/5/19 14:28
 * @Version 1.0
 * com.example.config
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";

    String desc() default "";
}
