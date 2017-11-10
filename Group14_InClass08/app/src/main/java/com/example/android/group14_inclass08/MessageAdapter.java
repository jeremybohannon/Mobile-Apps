package com.example.android.group14_inclass08;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

// Jeremy Bohannon Elizabeth Thompson
// In class 08
// messageadapter.java
public class MessageAdapter extends ArrayAdapter<MessageObject> {
    private final OkHttpClient client = new OkHttpClient();

    private List<MessageObject> messages;
    ChatActivity activity;

    public MessageAdapter(ChatActivity activity, Context context, int resource, List<MessageObject> messages) {
        super(context, resource, messages);
        this.activity = activity;
        this.messages = messages;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        MessageViewHolder MessageViewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.chat_view, parent, false);

            MessageViewHolder = new MessageViewHolder();


            assert view != null;
            MessageViewHolder.user = view.findViewById(R.id.user);
            MessageViewHolder.date = view.findViewById(R.id.date);
            MessageViewHolder.message = view.findViewById(R.id.message);
            MessageViewHolder.delete = view.findViewById(R.id.deleteBtn);

            view.setTag(MessageViewHolder);
        } else {
            MessageViewHolder = (MessageViewHolder) view.getTag();
        }

        final MessageObject object = getItem(position);

        MessageViewHolder.message.setText(object.getMessage());
        MessageViewHolder.user.setText(object.getUser_fname() + " " + object.getUser_lname());

        PrettyTime p = new PrettyTime();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.getCreated_at());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MessageViewHolder.date.setText(p.format(date));


        if(Integer.parseInt(object.getUser_id()) == activity.returnUserId()){

            MessageViewHolder.delete.setVisibility(View.VISIBLE);

            MessageViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Thread clicked: " + object.getMessage());

                    Request request = new Request.Builder()
                            .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/message/delete/" + object.getId())
                            .header("Authorization", "BEARER " + activity.returnUserToken())
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try {
                                ResponseBody responseBody = response.body();

                                if (!response.isSuccessful()) {
                                    throw new IOException("Unexpected code " + response);
                                }

                                assert responseBody != null;
                                System.out.println("DELETE RESPONSE: " + responseBody.toString());
                                messages.remove(getItem(position));
                                activity.updateThread();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

        } else {
            MessageViewHolder.delete.setVisibility(View.GONE);
        }


        return view;
    }

}
