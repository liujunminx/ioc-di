package com.ljm;

import com.ljm.core.PropertiesReader;

import java.util.Map;

public class IocApplication {
    public static void main(String[] args) {
        PropertiesReader reader = new PropertiesReader();
        Map<String, Object> propertiesMap = reader.getPropertiesMap();
        System.out.println(propertiesMap);
    }
}
