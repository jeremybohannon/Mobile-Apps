package com.example.android.group14_inclass08;

public class ThreadObject {
    private String title;

    public ThreadObject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ThreadObject{" +
                "title='" + title + '\'' +
                '}';
    }
}
