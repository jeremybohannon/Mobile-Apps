package com.example.android.group14_inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    TextView threadView;
    EditText chatText;
    ListView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        threadView = (TextView) findViewById(R.id.thread);
        chatText = (EditText) findViewById(R.id.chatText);
        chatList = (ListView) findViewById(R.id.chatList);



        final ArrayList<MessageObject> messages = new ArrayList<>();

//        final MessageAdapter messageAdapter = new MessageAdapter(this, R.layout.message_view, MessageObject);
//        chatList.setAdapter(messageAdapter);

        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                int index = i;

                MessageObject object = messages.get(i);


                System.out.println("Object: " + object.getName());

                messages.remove(i);

//                messageAdapter.notifyDataSetChanged();
            }
        });

    }
}
