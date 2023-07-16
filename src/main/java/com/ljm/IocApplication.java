package com.ljm;

import com.ljm.core.ApplicationReader;

import java.util.Map;

public class IocApplication {
    public static void main(String[] args) {
        ApplicationReader reader = new ApplicationReader();
        Map<String, Object> propertiesMap = reader.getPropertiesMap();
        System.out.println(propertiesMap);
    }
}
