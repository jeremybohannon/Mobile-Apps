package com.example.android.group14_inclass09;

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
//Jeremy Bohannon Elizabeth Thompson
//InClass09
//customadapter.java
public class CustomAdapter extends ArrayAdapter<Contact> {

    private List<Contact> objects;

    public CustomAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_contact, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.email = (TextView) view.findViewById(R.id.email);
            viewHolder.phone = (TextView) view.findViewById(R.id.phone);
            viewHolder.department = (TextView) view.findViewById(R.id.department);
            viewHolder.avatar = view.findViewById(R.id.avatar);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Contact object = getItem(position);

        viewHolder.name.setText(object.getName());
        viewHolder.email.setText(object.getEmail());
        viewHolder.phone.setText(object.getPhone());
        viewHolder.department.setText(object.getDepartment());

        viewHolder.avatar.setImageResource(Integer.parseInt(object.getImageid()));

        return view;
    }

}
