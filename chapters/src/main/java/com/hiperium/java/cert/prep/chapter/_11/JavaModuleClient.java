package com.hiperium.java.cert.prep.chapter._11;

import com.hiperium.java.cert.prep.chapter._11.api.Chapter11API;
import com.hiperium.java.cert.prep.chapter._11.api.entity.Actor;

import java.util.List;
import java.util.ServiceLoader;

public class JavaModuleClient {

    private static ServiceLoader<Chapter11API> loader = ServiceLoader.load(Chapter11API.class);
    private static Chapter11API api;

    static {
        api = loader.findFirst().orElseThrow(() -> new IllegalStateException("API Service Implementation not found."));
    }

    public static void main(String[] args) {
        System.out.println("Getting all actors of the Big Bang Theory...");
        List<Actor> actors = api.findAll();
        System.out.println(actors);
    }
}
