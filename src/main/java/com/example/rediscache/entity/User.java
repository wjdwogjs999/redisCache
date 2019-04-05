package com.example.rediscache.entity;


import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String role;

    public User() {
    }

    public User(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
