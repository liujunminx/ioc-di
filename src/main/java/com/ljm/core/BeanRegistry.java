package com.ljm.core;

public interface BeanRegistry {

    void registerBean(Class<?> clazz);

    void removeBean(Class<?> clazz);

    boolean containsBean(Class<?> clazz);
}
