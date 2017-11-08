package com.example.android.group14_inclass08;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;


public class ThreadAdapter extends ArrayAdapter<ThreadObject> {
    private final OkHttpClient client = new OkHttpClient();

    private List<ThreadObject> objects;
    ThreadActivity activity;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public ThreadAdapter(ThreadActivity activity, Context context, int resource, List<ThreadObject> objects) {
        super(context, resource, objects);
        this.activity = activity;
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        ThreadViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.thread_view, parent, false);

            viewHolder = new ThreadViewHolder();

            viewHolder.thread = view.findViewById(R.id.thread);
            viewHolder.delete = view.findViewById(R.id.deleteBtn);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ThreadViewHolder) view.getTag();
        }

        final ThreadObject object = getItem(position);

        viewHolder.thread.setText(object.getTitle());

        System.out.println("OBJECT ID: " + object.getUser_id());
        System.out.println("ACTIVITY ID " + activity.returnUserId());

        if(Integer.parseInt(object.getUser_id()) == activity.returnUserId()){

            viewHolder.delete.setVisibility(View.VISIBLE);

            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Thread clicked: " + object.getTitle());

                    Request request = new Request.Builder()
                            .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/delete/" + object.getId())
                            .header("Authorization", "BEARER " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MDk2ODY2OTYsImV4cCI6MTU0MTIyMjY5NiwianRpIjoiMkdKV2c3U0hKS3NiT2IyZVNkVzFWayIsInVzZXIiOjF9.rRTLX3i-kFYxAtbhUXrqQKDxXs0KoTEgV4iRX2q3p5M")
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override public void onResponse(Call call, Response response) throws IOException {
                            try {
                                ResponseBody responseBody = response.body();

                                if (!response.isSuccessful()) {
                                    throw new IOException("Unexpected code " + response);
                                }

                                System.out.println("DELETE RESPONSE: " + responseBody.toString());
                                objects.remove(getItem(position));
                                activity.updateThread();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

        } else {
            viewHolder.delete.setVisibility(View.GONE);
        }

        return view;
    }

}


