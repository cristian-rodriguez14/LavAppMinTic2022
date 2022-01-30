package com.example.lavapp.model;

public class User {

    private String uid,name;

    public User(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
