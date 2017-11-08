package com.example.android.group14_inclass08;

import java.io.Serializable;

public class ThreadObject implements Serializable {
    private String title;
    private String user_id;
    private String id;

    public ThreadObject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "ThreadObject{" +
                "title='" + title + '\'' +
                '}';
    }
}
