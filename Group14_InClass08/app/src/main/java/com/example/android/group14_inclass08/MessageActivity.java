package com.example.android.group14_inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    TextView nameView;
    EditText threadText;
    ListView threadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        nameView = (TextView) findViewById(R.id.nameView);
        threadText = (EditText) findViewById(R.id.threadText);
        threadList = (ListView) findViewById(R.id.chatList);

//        final MessageAdapter messageAdapter = new MessageAdapter(this, R.layout.message_view, MessageObject);
//        threadList.setAdapter(messageAdapter);




    }
}
