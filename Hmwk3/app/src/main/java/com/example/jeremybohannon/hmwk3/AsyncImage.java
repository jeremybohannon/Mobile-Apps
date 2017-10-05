package com.example.jeremybohannon.hmwk3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jeremybohannon on 10/4/17.
 */


public class AsyncImage extends AsyncTask<String, Void, String> {

    TriviaActivity activity;
    Bitmap bitmap;

    public AsyncImage(TriviaActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection;
        bitmap = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (Exception e) {
            //Handle the exceptions
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);

        activity.setPhoto(bitmap);
    }
}
