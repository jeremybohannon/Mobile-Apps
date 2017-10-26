package com.example.android.group14_hw05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
//Jeremy Bohannon Elizabeth Thompson
//Homework 5
// CustomAdapter.java
public class CustomAdapter extends ArrayAdapter<DataObject> {

    private List<DataObject> objects;

    public CustomAdapter(Context context, int resource, List<DataObject> objects) {
        super(context, resource, objects);
        this.objects = objects;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.title = view.findViewById(R.id.title);
            viewHolder.image = view.findViewById(R.id.image);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        DataObject object = getItem(position);

        viewHolder.title.setText(object.getTitle());
        Picasso.with(this.getContext()).load(object.getSmallImageURL()).into(viewHolder.image);

        if(object.getisHighlighted()){
            view.setBackgroundResource(R.color.highlight);
        } else {
            view.setBackgroundResource(R.color.deafult);
        }

        return view;
    }

}
