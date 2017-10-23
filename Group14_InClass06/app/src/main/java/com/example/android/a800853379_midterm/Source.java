package com.example.android.a800853379_midterm;

import java.io.Serializable;

/**
 * Created by jeremybohannon on 10/16/17.
 */

public class Source implements Serializable {
    String id;
    String name;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
