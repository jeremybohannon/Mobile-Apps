package com.example.android.group14_inclass08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ThreadActivity extends AppCompatActivity {

    TextView nameView;
    EditText threadText;
    ListView threadList;
    ImageView logoutBtn, addBtn;
    User user;
    String userToken;
    static ThreadAdapter threadAdapter;

    private final OkHttpClient client = new OkHttpClient();

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        nameView = (TextView) findViewById(R.id.threadView);
        threadText = (EditText) findViewById(R.id.chatText);
        threadList = (ListView) findViewById(R.id.chatList);
        logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
        addBtn = (ImageView) findViewById(R.id.addBtn);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        userToken = prefs.getString("userToken", "");

        nameView.setText(user.getUser_fname() + " " + user.getUser_lname());

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = threadText.getText().toString();

                makeThread(title);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setToken("");

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("userToken", "");
                editor.apply();

                finish();
            }
        });

        getThreads();

    }

    public void getThreads(){
        //TODO get from shared pref
        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread")
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
                    Thread thread = gson.fromJson(responseBody.string(), Thread.class);

                    loadThreads(thread);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadThreads(Thread thread){
        threadList = (ListView) findViewById(R.id.threadList);
        final List<ThreadObject> threads = thread.getThreads();
        threadAdapter = new ThreadAdapter(ThreadActivity.this, this, R.layout.thread_view, threads);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                threadList.setAdapter(threadAdapter);

                threadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                        ThreadObject object = threads.get(i);

                        System.out.println("Object: " + object.getTitle());
                        System.out.println("ID: " + object.getUser_id());
                        System.out.println("User id: " + user.getUser_id());
                        Intent intent = new Intent(ThreadActivity.this, ChatActivity.class);
                        intent.putExtra("Thread", object);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void makeThread(String title){
        RequestBody formBody = new FormBody.Builder()
                .add("title", title)
                .build();

        Request request = new Request.Builder()
                .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/add")
                .header("Authorization", "BEARER " + returnUserToken())
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    System.out.println("Created thread...");
                    //TODO make not bad... like omg... this is so bad.. please don't judge

                    getThreads();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void updateThread(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                threadAdapter.notifyDataSetChanged();
            }
        });
    }

    public int returnUserId(){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int userId = 0;
        userId = prefs.getInt("userID", 0);
        return userId;
    }

    public String returnUserToken(){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String userId = "";
        userId = prefs.getString("userToken", "");
        return userId;
    }

}
