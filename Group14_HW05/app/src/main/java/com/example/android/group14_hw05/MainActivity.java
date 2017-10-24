package com.example.android.group14_hw05;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<DataObject> objects = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading News...");
        progressDialog.show();

        String URL = "https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml";

        new GetDataXML(this).execute(URL);

    }

    public void handleData(final ArrayList<DataObject> objects){
        lv = (ListView) findViewById(R.id.listView);

        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.item_layout, objects);
        lv.setAdapter(customAdapter);

        progressDialog.dismiss();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                int index = i;

                DataObject object = objects.get(i);


                System.out.println("Object: " + object.getTitle());

            }
        });
    }
}
