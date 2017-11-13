package com.example.android.group14_inclass08;

import java.io.Serializable;

// Jeremy Bohannon Elizabeth Thompson
// In class 08
// user.java

public class User implements Serializable {
    String status, token, user_email, user_fname, user_lname, user_role;
    int user_id;

    @Override
    public String toString() {
        return "User{" +
                "status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lame='" + user_lname + '\'' +
                ", user_role='" + user_role + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
