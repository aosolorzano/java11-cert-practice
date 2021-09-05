package com.hiperium.java.cert.prep.chapter._11.api;

import com.hiperium.java.cert.prep.chapter._11.bo.Chapter11BO;

import java.util.List;
import java.util.Objects;

public class Chapter11API {
    private static Chapter11API instance;
    private final Chapter11BO bo = Chapter11BO.getInstance();

    private Chapter11API() {
    }

    public static Chapter11API getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Chapter11API();
        }
        return instance;
    }

    public String findActor(String name) {
        return bo.findActor(name);
    }

    public void addActor(String name) {
        bo.addActor(name);
    }

    public List<String> findAll() {
        return bo.findAll();
    }
}
