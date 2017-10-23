package com.example.android.a800853379_midterm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jeremybohannon on 10/16/17.
 */

public class GetImageAsync extends AsyncTask<String, Void, String> {
    ImageView imageView;
    Bitmap bitmap;

    GetImageAsync(ImageView imageView){
        this.imageView = imageView;
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        imageView.setImageBitmap(bitmap);
    }
}
