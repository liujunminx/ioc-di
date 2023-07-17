package com.ljm.core;

public interface BeanRegistry {

    void registerBean(String beanName, Class<?> clazz);

    void removeBean(String beanName);

    boolean containsBean(String beanName);
}
