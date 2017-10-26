package com.example.android.group14_hw05;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
//Jeremy Bohannon Elizabeth Thompson
//Homework 5
// ItemDetailsActivity.java
public class ItemDetailsActivity extends AppCompatActivity {

    TextView titleView, updatedDataView, summaryView;
    ImageView imageView;

    DataObject item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        titleView = (TextView) findViewById(R.id.titleView);
        updatedDataView = (TextView) findViewById(R.id.updatedDateView);
        summaryView = (TextView) findViewById(R.id.summaryView);

        imageView = (ImageView) findViewById(R.id.imageView);


        Intent intent = getIntent();
        item = (DataObject) intent.getSerializableExtra("Item_Data");

        titleView.setText(item.getTitle());

        Date newDate = null;
        String formatDate = "";
        int hour = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            try {
                newDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(item.getUpdatedDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            formatDate = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(newDate);
            hour = Integer.parseInt(new SimpleDateFormat("HH").format(newDate));
        }


        formatDate += hour < 12 ? " AM" : " PM";
        updatedDataView.setText(formatDate);

        summaryView.setText(item.getSummary());

        Picasso.with(this).load(item.getLargeImageURL()).into(imageView);


    }
}
