package com.example.jeremybohannon.hw2_group14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button createNewBtn, editBtn, deleteBtn, displayBtn, finishBtn;
    int REQ_CODE = 100;
    final static String RESULT_KEY = "";
    static int idCounter = 0;
    static Boolean isDelete = false;
    static Boolean isEdit = false;
    static Boolean isDisplay = false;

    static ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        createNewBtn = (Button) findViewById(R.id.createNewBtn);
        editBtn = (Button) findViewById(R.id.editBtn);
        deleteBtn = (Button) findViewById(R.id.deleteContact);
        displayBtn = (Button) findViewById(R.id.displayContact);
        finishBtn = (Button) findViewById(R.id.finishBtn);

        createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEdit = false;
                isDelete = false;
                isDisplay = false;
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDelete = false;
                isDisplay = false;
                isEdit = true;
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEdit = false;
                isDisplay = false;
                isDelete = true;
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEdit = false;
                isDelete = false;
                isDisplay = true;
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


    }








}
