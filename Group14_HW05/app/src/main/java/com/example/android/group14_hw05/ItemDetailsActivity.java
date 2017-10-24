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

        String date = item.getUpdatedDate().substring(0, item.getUpdatedDate().indexOf('T'));
        String time = item.getUpdatedDate().substring(item.getUpdatedDate().indexOf('T') + 1, item.getUpdatedDate().length());

        updatedDataView.setText(date);

        summaryView.setText(item.getSummary());

        Picasso.with(this).load(item.getLargeImageURL()).into(imageView);


    }
}
