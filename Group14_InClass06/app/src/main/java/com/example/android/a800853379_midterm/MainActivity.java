package com.example.android.a800853379_midterm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//In class 06
//Jeremy Bohannon Elizabeth Thompson
//Main activity
public class MainActivity extends AppCompatActivity {

    ArrayList<Source> sources = new ArrayList<>();
    ProgressDialog progress;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = "https://newsapi.org/v1/sources";

        progress = ProgressDialog.show(this, "Loading...",
                "", true);

        new GetDataJSON(this).execute(URL);
    }

    public void setUpListView(){
        System.out.println("In set up list view");
        lv = (ListView) findViewById(R.id.listView);

        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.source_layout, sources);
        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                Source source = sources.get(i);

                System.out.println("Object: " + source.getName());

                startIntent(source);

            }
        });
    }

    public void startIntent(Source source){
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        intent.putExtra("Source_Object", source);
        startActivity(intent);

    }

    public void handleData(ArrayList<Source> sources){
        this.sources = sources;
        setUpListView();
        progress.dismiss();
    }


}
