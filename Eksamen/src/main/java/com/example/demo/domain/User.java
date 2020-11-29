package com.example.demo.domain;

public class User {

    public int id;
    public String mail;
    public String password;

    public User(int id, String mail, String password) {
        this.id = id;
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getId() {

        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", mail='" + mail + '\'' + ", password='" + password + '\'' + '}';
    }
}