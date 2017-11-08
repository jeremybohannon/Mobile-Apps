package com.example.android.group14_inclass08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ChatActivity extends AppCompatActivity {

    TextView threadView;
    EditText chatText;
    ListView chatList;
    ThreadObject thread;
    String threadId, userToken;
    ListView messageList;
    static MessageAdapter messageAdapter;


    private final OkHttpClient client = new OkHttpClient();
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        threadView = (TextView) findViewById(R.id.threadView);
        chatText = (EditText) findViewById(R.id.chatText);
        chatList = (ListView) findViewById(R.id.chatList);

        Intent intent = getIntent();
        thread = (ThreadObject) intent.getSerializableExtra("Thread");

        threadView.setText(thread.getTitle());
        threadId = thread.getId();

        final ArrayList<MessageObject> messages = new ArrayList<>();


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        userToken = prefs.getString("userToken", "");

        getMessages(threadId);

    }

    public void getMessages(String threadId){
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/messages/" + threadId)
                .header("Authorization", "BEARER " + userToken)
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
                    Message message = gson.fromJson(responseBody.string(), Message.class);

                    loadMessages(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadMessages(Message message){
        messageList = (ListView) findViewById(R.id.chatList);
        final List<MessageObject> messages = message.getMessages();
        messageAdapter = new MessageAdapter(ChatActivity.this, this, R.layout.chat_view, messages);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.setAdapter(messageAdapter);
            }
        });
    }

    public void updateThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.notifyDataSetChanged();
            }
        });
    }
}
