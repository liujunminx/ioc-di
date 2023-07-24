package com.ljm;

import com.ljm.component.HelloController;
import com.ljm.core.Context;
import com.ljm.core.PropertiesReader;

import java.util.Map;

public class IocApplication {
    public static void main(String[] args) {
        Context context = new Context();
        HelloController helloController = context.getBean(HelloController.class);
        helloController.hello();
    }
}
