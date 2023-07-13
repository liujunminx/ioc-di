package com.ljm.component;


import com.ljm.annotation.Component;
import com.ljm.annotation.Inject;

@Component
public class HelloController {

    @Inject
    private HelloService helloService;

    public void hello() {
        helloService.hello();
    }
}
