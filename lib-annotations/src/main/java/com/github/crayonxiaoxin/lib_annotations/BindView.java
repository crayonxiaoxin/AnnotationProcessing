package com.github.crayonxiaoxin.lib_annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * <p>
 * Retention SOURCE  注解处理器的注解仅需要保留到编译时
 * </p>
 * <p>
 * Target    FIELD   作用域是 成员变量
 * </p>
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
