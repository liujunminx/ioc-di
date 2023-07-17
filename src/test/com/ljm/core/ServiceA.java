package com.ljm.core;


import com.ljm.annotation.Inject;

public class ServiceA {

    @Inject
    ServiceBImpl serviceB;

    public ServiceA() {

    }

    public void hello() {
        serviceB.hello();
    }
}
