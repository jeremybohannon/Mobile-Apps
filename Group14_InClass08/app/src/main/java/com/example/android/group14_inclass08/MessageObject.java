package com.example.android.group14_inclass08;

/**
 * Created by jeremybohannon on 10/15/17.
 */

public class MessageObject {
    private String message;
    private String name;
    private String time;

    public MessageObject(String message, String name, String time) {
        this.message = message;
        this.name = name;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
