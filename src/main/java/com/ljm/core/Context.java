package com.ljm.core;

import com.ljm.annotation.Inject;
import com.ljm.component.HelloController;
import com.ljm.consts.PropertiesConst;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private final BeanFactory beanFactory = ConcreteBeanFactory.getInstance();

    private final BeanRegistry beanRegistry = ConcreteBeanFactory.getInstance();

    private final PropertiesReader reader = new PropertiesReader();

    public Context() {
        init();
    }

    private void init(){
        Map<String, Object> propertiesMap = reader.getPropertiesMap();
        String componentPackage = (String) propertiesMap.get(PropertiesConst.CONTAIN_PACKAGE_KEY);
        IocHelper iocHelper = new IocHelper(beanRegistry);
        try {
            iocHelper.scanComponentPackage(componentPackage);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("container init failed: " + e.getMessage() , e);
        }
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public <T> T getBean(Class<T> requiredType){
        return getBeanFactory().getBean(requiredType);
    }
}
