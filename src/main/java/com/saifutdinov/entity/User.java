package com.saifutdinov.entity;

public class User {

    private Integer id;

    private String login;
    private String password;

    private String name;
    private String lastname;

    public User(Integer id, String login, String password, String name, String lastname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }

    public User(){}

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }
}