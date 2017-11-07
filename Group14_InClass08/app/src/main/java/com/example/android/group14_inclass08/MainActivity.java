package com.example.android.group14_inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private final OkHttpClient client = new OkHttpClient();
}
