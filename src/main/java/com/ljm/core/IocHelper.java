package com.ljm.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;

public class IocHelper {

    private BeanRegistry registry;

    public IocHelper(BeanRegistry registry){
        this.registry = registry;
    }

    public void scanComponentPackage(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(resource.getFile());
            if (directory.exists()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file: files){
                        if (file.isDirectory()) {
                            scanComponentPackage("." + file.getName());
                        }else {
                            if (file.getName().endsWith(".class")){
                                String beanName = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                                Class<?> clazz = Class.forName(beanName);
                                registry.registerBean(clazz);
                            }
                        }
                    }
                }
            }
        }
    }
}
