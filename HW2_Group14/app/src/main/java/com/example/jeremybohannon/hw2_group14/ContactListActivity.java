package com.example.jeremybohannon.hw2_group14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    Intent intent = new Intent();
    private ListView listView;
    int REQ_CODE = 100;
    final static String RESULT_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        if(MainActivity.isDelete){
            setTitle("Delete Contact");
        } else if (MainActivity.isDisplay) {
            setTitle("Display Contact");
        } else {
            setTitle("Contact List");
        }

        listView = (ListView) findViewById(R.id.contactListView);

        final List<Contact> contacts = MainActivity.contacts;
        final List<ContactListItem> items = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            items.add(new ContactListItem(contact.getPicture(), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getId()));
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.id.firstName, items);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                if (MainActivity.isDelete) {
                    int id = items.get(i).getId();
                    for (int j = 0; j < MainActivity.contacts.size(); j++) {
                        if (MainActivity.contacts.get(j).getId() == id) {
                            MainActivity.contacts.remove(j);
                            setResult(RESULT_OK, intent);
                            finish();
                            return;
                        }
                    }
                } else if (MainActivity.isDisplay) {
                    Intent intent = new Intent(ContactListActivity.this, ContactDetailsActivity.class);
                    intent.putExtra("EXTRA_IDENTIFIER", items.get(i).getId());
                    startActivityForResult(intent, REQ_CODE);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    MainActivity.isEdit = true;
                    Intent intent = new Intent(ContactListActivity.this, NewContactActivity.class);
                    intent.putExtra("EXTRA_IDENTIFIER", items.get(i).getId());
                    startActivityForResult(intent, REQ_CODE);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
