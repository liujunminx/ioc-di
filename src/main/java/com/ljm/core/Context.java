package com.ljm.core;

import com.ljm.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Context {

    private static Map<Class<?>, Object> containerMap = new HashMap<>();

    public static <T> void createContainer(Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        for (Field field: clazz.getDeclaredFields()){
            if (field.isAnnotationPresent(Inject.class)){
                Class<?> fieldClass = Class.forName(field.getType().getName());
                Object fieldObj = fieldClass.getDeclaredConstructor().newInstance();
                // set值注入
                field.setAccessible(true);
                field.set(obj, fieldObj);
            }
        }
        containerMap.put(clazz, obj);
    }

    public static <T> T getContainer(Class<T> clazz) {
        return (T) containerMap.get(clazz);
    }
}
