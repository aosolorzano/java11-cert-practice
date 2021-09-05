package com.hiperium.java.cert.prep.chapter._11;

import com.hiperium.java.cert.prep.chapter._11.api.Chapter11API;

import java.util.List;

public class Actors {
    public static void main(String[] args) {
        System.out.println("Getting all actors of the Big Bang Theory...");
        List<String> actors = Chapter11API.getInstance().findAll();
        System.out.println(actors.toString());
    }
}
