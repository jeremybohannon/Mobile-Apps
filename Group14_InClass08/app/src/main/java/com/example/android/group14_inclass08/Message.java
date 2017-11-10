package com.example.android.group14_inclass08;

import java.util.ArrayList;

// Jeremy Bohannon Elizabeth Thompson
// In class 08
// message.java
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
