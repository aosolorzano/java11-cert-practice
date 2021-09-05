package com.hiperium.java.cert.prep.chapter._11.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Chapter11DAO {
    private static final List<String> USERS = Arrays.asList("Penny", "Sheldon", "Leonard", "Amy", "Raj", "Howard", "Bernadette");
    private static Chapter11DAO instance;

    private Chapter11DAO() {
    }

    public static Chapter11DAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Chapter11DAO();
        }
        return instance;
    }

    public String findActor(String name) {
        return USERS.stream().filter(s -> s.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void addActor(String name) {
        String existingActor = this.findActor(name);
        if (Objects.isNull(existingActor)) {
            USERS.add(name);
        }
    }

    public List<String> findAll() {
        return USERS;
    }
}
