package com.example.android.group14_hw05;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetDataXML extends AsyncTask<String, Void, ArrayList<DataObject>> {
    private MainActivity activity;

    public GetDataXML(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected ArrayList<DataObject> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        ArrayList<DataObject> result = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = ItemParser.ItemsPullParser.parseItems(connection.getInputStream());
            }
        } catch (Exception e) {
            System.out.println("Exception oh darn: " + e);
        } finally {
            //Close open connections and reader
        }

        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<DataObject> result) {
        super.onPostExecute(result);

        System.out.println("GOT RESULT: " + result);

        this.activity.handleData(result);
    }
}
