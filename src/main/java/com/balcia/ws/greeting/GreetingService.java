package com.balcia.ws.greeting;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public Greeting greeting(String name) {
        return new Greeting("hello " + name + "\n");
    }

}