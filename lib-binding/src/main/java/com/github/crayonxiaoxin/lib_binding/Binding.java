package com.github.crayonxiaoxin.lib_binding;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Binding {

    /**
     * 找到 activity 生成对应的 class，并调用其构造函数，实现 findViewById
     * @param activity
     */
    public static void bind(Activity activity) {
        String name = activity.getClass().getCanonicalName();
        try {
            // 找到生成的对应的 binding
            Class clazz = Class.forName(name + "$Binding");
            // 获取参数为 activity.getClass() 的构造器
            Constructor constructor = clazz.getDeclaredConstructor(activity.getClass());
            constructor.setAccessible(true);
            // 调用这个构造器
            constructor.newInstance(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
