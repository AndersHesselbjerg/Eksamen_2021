package com.example.demo.domain;

public class User {
    public int id;
    public String mail;
    public String password;
    public int isAdmin;
    public int adminID;



    public User(){


    }

    public User(int id, String mail, String password, int isAdmin, int adminID) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.isAdmin = isAdmin;
        this.adminID = adminID;
    }

    public User(int id, String mail, String password, int adminID) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.adminID = adminID;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }
    public User(String mail, String password, int isAdmin) {
        this.mail = mail;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String mail){
        this.mail = mail;
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", adminID=" + adminID +
                '}';
    }
}