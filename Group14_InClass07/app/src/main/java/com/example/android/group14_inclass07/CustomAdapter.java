package com.example.android.group14_inclass07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jeremybohannon on 10/15/17.
 */

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
            view = LayoutInflater.from(getContext()).inflate(R.layout.data_view, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            viewHolder.picture = view.findViewById(R.id.pictureView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        DataObject object = getItem(position);

        viewHolder.name.setText(object.getName());
        viewHolder.price.setText(object.getPrice());
        viewHolder.picture.setImageResource(object.getPicture());


        return view;
    }

}
