package com.ljm.core;

import com.ljm.annotation.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcreteBeanFactory implements BeanFactory, BeanRegistry {

    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>(256);

    private static volatile ConcreteBeanFactory instance;

    private ConcreteBeanFactory() {}

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return (T) beanMap.get(requiredType);
    }

    public static ConcreteBeanFactory getInstance() {
        if (instance == null) {
            synchronized (ConcreteBeanFactory.class){
                if (instance == null) {
                    instance = new ConcreteBeanFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public void registerBean(Class<?> clazz) {
        try {
            if (!beanMap.containsKey(clazz)){
                Object instance = clazz.getDeclaredConstructor().newInstance();
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields){
                    Class<?> type = field.getType();
                    Object injectObj;
                    if (beanMap.containsKey(type)){
                        injectObj = beanMap.get(type);
                    }else {
                        injectObj = type.getDeclaredConstructor().newInstance();
                    }
                    field.setAccessible(true);
                    field.set(instance, injectObj);
                }
                beanMap.put(clazz, instance);
            }
        }catch (Exception e){
            throw new RuntimeException("instance inject field failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void removeBean(Class<?> clazz) {
        beanMap.remove(clazz);
    }

    @Override
    public boolean containsBean(Class<?> clazz) {
        return beanMap.containsKey(clazz);
    }
}
