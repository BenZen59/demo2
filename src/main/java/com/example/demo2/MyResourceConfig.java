package com.example.demo2;

import jakarta.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;

@Provider
public class MyResourceConfig extends ResourceConfig {
    public MyResourceConfig() {
        packages(true, "mtitek.swagger.samples");
    }
}