package com.github.crayonxiaoxin.lib_reflection;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * <p>
 * Retention RUNTIME 反射需要保留到运行时
 * </p>
 * <p>
 * Target    FIELD   作用域是 成员变量
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindView {
    //    int id() default 1;
    @IdRes int value();
}
