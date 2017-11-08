package com.example.android.group14_inclass08;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jeremybohannon on 11/6/17.
 */

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
