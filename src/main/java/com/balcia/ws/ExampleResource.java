package com.balcia.ws;

import com.balcia.ws.greeting.Greeting;
import com.balcia.ws.greeting.GreetingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/greeting/{name}")
    public Greeting greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
}
