package com.hogo.rahul.databaselr.Model;

/**
 * Created by rahul on 2/11/17.
 */

public class DatabaseModel {
    private String FullName;
    private String Password;
    private String email;


    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
