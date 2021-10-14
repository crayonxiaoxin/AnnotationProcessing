package com.github.crayonxiaoxin.lib_reflection;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class Binding {
    /**
     * 静态方法 绑定
     *
     * @param activity 当前 activity
     * <p>
     * 反射遍历所有成员变量，找到注解 BindView , findViewById 并赋值
     * </p>
     */
    public static void bind(Activity activity) {
        for (Field declaredField : activity.getClass().getDeclaredFields()) {
            BindView bindView = declaredField.getAnnotation(BindView.class);
            if (bindView != null) {
                int resId = bindView.value();
                View view = activity.findViewById(resId);
                try {
                    declaredField.setAccessible(true); // 允许访问
                    declaredField.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
