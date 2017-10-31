package com.example.android.group14_inclass07;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactList.OnFragmentInteractionListener, CreateNewContact.OnFragmentInteractionListener, SelectAvatar.OnFragmentInteractionListener {

    ArrayList<Contact> contacts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, ContactList.newInstance(contacts), "contactList")
                .commit();
    }

    public void onListFragmentInteraction() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CreateNewContact(), "createNewContact")
                .commit();
    }

    @Override
    public void onListFragementInteraction(int index) {
        contacts.remove(index);
    }

    @Override
    public void onCreateFragmentInteraction() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SelectAvatar(), "selectAvatar")
                .commit();
    }

    @Override
    public void onCreateFragmentInteraction(Contact contact) {
        contacts.add(contact);

        System.out.println("NAME: " + contact.getName());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ContactList.newInstance(contacts), "contactList")
                .commit();
    }


    @Override
    public void onSelectFragmentInteraction() {

    }
}
