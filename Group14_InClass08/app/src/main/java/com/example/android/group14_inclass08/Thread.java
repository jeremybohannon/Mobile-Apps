package com.example.android.group14_inclass08;

import java.io.Serializable;
import java.util.ArrayList;

// Jeremy Bohannon Elizabeth Thompson
// In class 08
// thread.java
public class Thread implements Serializable {
    ArrayList<ThreadObject> threads;

    public ArrayList<ThreadObject> getThreads() {
        return threads;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "threads=" + threads.toString() +
                '}';
    }
}
