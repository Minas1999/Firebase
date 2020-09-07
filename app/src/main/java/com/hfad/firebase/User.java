package com.hfad.firebase;

public class User {
    public String name;
    public String lastName;
    public String mail;
    public String id;

    public User(){

    }

    public User(String name, String lastName, String mail, String id) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.id = id;
    }
}
