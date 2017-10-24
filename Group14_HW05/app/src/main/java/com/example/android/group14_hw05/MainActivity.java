package com.example.android.group14_hw05;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<DataObject> objects = new ArrayList<>();
    ProgressDialog progressDialog;
    EditText editText;
    Button clear, go;

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
        editText = (EditText) findViewById(R.id.search);
        go = (Button) findViewById(R.id.go);
        clear = (Button) findViewById(R.id.clear);


        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.item_layout, objects);
        lv.setAdapter(customAdapter);

        progressDialog.dismiss();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                int index = i;

                DataObject object = objects.get(i);

                System.out.println("Object: " + object.getTitle());

                Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                intent.putExtra("Item_Data", object);
                startActivity(intent);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = editText.getText().toString().toLowerCase();

                for(int i = 0; i < objects.size(); i++){
                    if(objects.get(i).getTitle().toLowerCase().contains(search)){
                        DataObject tmp = objects.get(i);
                        tmp.setisHighlighted(true);
                        objects.remove(i);
                        objects.add(0, tmp);



                    }
                }
                customAdapter.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(DataObject object: objects){
                    object.setisHighlighted(false);
                }
                customAdapter.notifyDataSetChanged();
            }
        });
    }
}
