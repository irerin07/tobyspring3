package com.tistory.irerin07.tobyspring3.user.domain;

/**
 * @since       2021.06.03
 * @author      민경수
 * @description user
 **********************************************************************************************************************/
public class User {

    String id;
    String name;
    String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}