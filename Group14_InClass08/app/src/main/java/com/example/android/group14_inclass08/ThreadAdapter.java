package com.example.android.group14_inclass08;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

// Jeremy Bohannon Elizabeth Thompson
// In class 08
// threadadapter.java
public class ThreadAdapter extends ArrayAdapter<ThreadObject> {
    private final OkHttpClient client = new OkHttpClient();

    private List<ThreadObject> objects;
    private ThreadActivity activity;

    ThreadAdapter(ThreadActivity activity, Context context, int resource, List<ThreadObject> objects) {
        super(context, resource, objects);
        this.activity = activity;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View view, @NonNull ViewGroup parent) {

        ThreadViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.thread_view, parent, false);

            viewHolder = new ThreadViewHolder();

            viewHolder.thread = view.findViewById(R.id.message);
            viewHolder.delete = view.findViewById(R.id.deleteBtn);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ThreadViewHolder) view.getTag();
        }

        final ThreadObject object = getItem(position);

        assert object != null;
        viewHolder.thread.setText(object.getTitle());

        if(Integer.parseInt(object.getUser_id()) == activity.returnUserId()){

            viewHolder.delete.setVisibility(View.VISIBLE);

            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Thread clicked: " + object.getTitle());

                    Request request = new Request.Builder()
                            .url("http://ec2-54-164-74-55.compute-1.amazonaws.com/api/thread/delete/" + object.getId())
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


