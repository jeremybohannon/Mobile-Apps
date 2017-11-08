package com.example.android.group14_inclass08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ThreadAdapter extends ArrayAdapter<ThreadObject> {

    private List<ThreadObject> objects;

    public ThreadAdapter(Context context, int resource, List<ThreadObject> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ThreadViewHolder viewHolder;

        if (view == null) {
//            view = LayoutInflater.from(getContext()).inflate(R.layout.thread_view, parent, false);

            viewHolder = new ThreadViewHolder();

//            viewHolder.thread = (TextView) view.findViewById(R.id.thread);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ThreadViewHolder) view.getTag();
        }


        ThreadObject object = getItem(position);

        viewHolder.thread.setText(object.getTitle());


        return view;
    }

}
