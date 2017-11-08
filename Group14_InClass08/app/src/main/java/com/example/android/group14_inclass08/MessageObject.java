package com.example.android.group14_inclass08;

/**
 * Created by jeremybohannon on 10/15/17.
 */

public class MessageObject {
    private String message;
    private String user_fname;
    private String user_lname;
    private String user_id;
    private String id;
    private String created_at;

    public MessageObject(String message, String user_fname, String user_lname, String user_id, String id, String created_at) {
        this.message = message;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_id = user_id;
        this.id = id;
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "message='" + message + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_id='" + user_id + '\'' +
                ", id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
