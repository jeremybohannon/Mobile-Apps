package com.example.android.group14_inclass08;

import java.util.ArrayList;

/**
 * Created by jeremybohannon on 11/8/17.
 */

public class Message {
    ArrayList<MessageObject> messages;

    public ArrayList<MessageObject> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messages=" + messages +
                '}';
    }
}
