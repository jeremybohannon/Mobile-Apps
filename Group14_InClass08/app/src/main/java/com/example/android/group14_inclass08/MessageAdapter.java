package com.example.android.group14_inclass08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ThreadObject> {

    private List<ThreadObject> threads;

    public MessageAdapter(Context context, int resource, List<ThreadObject> threads) {
        super(context, resource, threads);
        this.threads = threads;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        MessageViewHolder MessageViewHolder;

        if (view == null) {
//            view = LayoutInflater.from(getContext()).inflate(R.layout.blahmessage_view, parent, false);

            MessageViewHolder = new MessageViewHolder();

//            MessageViewHolder.message = view.findViewById(R.id.message);
//            MessageViewHolder.name = view.findViewById(R.id.name);
            MessageViewHolder.time = view.findViewById(R.id.time);

            view.setTag(MessageViewHolder);
        } else {
            MessageViewHolder = (MessageViewHolder) view.getTag();
        }


//        MessageObject object = getItem(position);

//        MessageViewHolder.message.setText(object.getMessage());
//        MessageViewHolder.name.setText(object.getName());
//        MessageViewHolder.time.setText(object.getTime());


        return view;
    }

}
