package com.ljm.core;

public interface BeanFactory {

    <T> T getBean(Class<T> requiredType);
}
