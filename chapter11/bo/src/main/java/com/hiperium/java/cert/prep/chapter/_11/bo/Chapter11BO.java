package com.hiperium.java.cert.prep.chapter._11.bo;

import com.hiperium.java.cert.prep.chapter._11.dao.Chapter11DAO;

import java.util.List;
import java.util.Objects;

public class Chapter11BO {

    private static Chapter11BO instance;
    private final Chapter11DAO dao = Chapter11DAO.getInstance();

    private Chapter11BO() {
    }

    public static Chapter11BO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Chapter11BO();
        }
        return instance;
    }

    public String findActor(String name) {
        return this.dao.findActor(name);
    }

    public void addActor(String name) {
        this.dao.addActor(name);
    }

    public List<String> findAll() {
        return this.dao.findAll();
    }
}
