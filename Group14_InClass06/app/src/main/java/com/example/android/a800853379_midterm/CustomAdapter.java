package com.example.android.a800853379_midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Source> {

    private List<Source> objects;

    public CustomAdapter(Context context, int resource, List<Source> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.source_layout, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.name = view.findViewById(R.id.sourceView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Source object = getItem(position);

        viewHolder.name.setText(object.getName());

        return view;
    }

}
