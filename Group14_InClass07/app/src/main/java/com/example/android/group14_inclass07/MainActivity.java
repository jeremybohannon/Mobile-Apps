package com.example.android.group14_inclass07;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactList.OnFragmentInteractionListener {

    ArrayList<Contact> contacts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ContactList(), "contactList")
                .commit();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
