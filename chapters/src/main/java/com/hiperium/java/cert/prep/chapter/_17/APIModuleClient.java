package com.hiperium.java.cert.prep.chapter._17;

import com.hiperium.java.cert.prep.chapter._11.api.Chapter11API;
import com.hiperium.java.cert.prep.chapter._11.api.entity.Actor;

import java.util.ServiceLoader;

public class APIModuleClient {

    private static ServiceLoader<Chapter11API> loader = ServiceLoader.load(Chapter11API.class);
    private static Chapter11API api;

    static {
        api = loader.findFirst().orElseThrow(() -> new IllegalStateException("API Service Implementation not found."));
    }

    public static void main(String[] args) {
        System.out.println("Getting Sheldon's data of the Big Bang Theory...");
        Actor actor = api.findByPersonage("Sheldon");
        System.out.println(actor);
    }
}
