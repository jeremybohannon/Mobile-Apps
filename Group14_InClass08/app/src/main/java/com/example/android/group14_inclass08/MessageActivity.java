package com.example.android.group14_inclass08;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MessageActivity extends AppCompatActivity {

    TextView nameView;
    EditText threadText;
    ListView threadList;
    ImageView logoutBtn, addBtn;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        nameView = (TextView) findViewById(R.id.nameView);
        threadText = (EditText) findViewById(R.id.threadText);
        threadList = (ListView) findViewById(R.id.chatList);
        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);

        ArrayList<MessageObject> messages = new ArrayList<>();

        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("User");

        nameView.setText(user.getUser_fname() + " " + user.getUser_lname());

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setToken("");
                finish();
            }
        });


        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread")
                .header("Authorization", "BEARER " + user.getToken())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();

                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Gson gson = new Gson();
                    Thread thread = gson.fromJson(responseBody.string(), Thread.class);
;
                    System.out.println("blah" + thread.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        final MessageAdapter messageAdapter = new MessageAdapter(this, R.layout.message_view, messages);
//        threadList.setAdapter(messageAdapter);




    }
}
