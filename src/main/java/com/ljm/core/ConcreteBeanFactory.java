package com.ljm.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcreteBeanFactory implements BeanFactory, BeanRegistry {

    private final Map<String, Class<?>> beanMap = new ConcurrentHashMap<>(256);

    @Override
    public void registerBean(String beanName, Class<?> clazz) {
        beanMap.put(beanName, clazz);
    }

    @Override
    public void removeBean(String beanName) {

    }

    @Override
    public boolean containsBean(String beanName) {
        return beanMap.containsKey(beanName);
    }


    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }
}
