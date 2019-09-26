package com.balcia.ws.greeting;

public class Greeting {
    private String message;

    Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
