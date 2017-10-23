package com.example.android.a800853379_midterm;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class GetDataJSON extends AsyncTask<String, Void, ArrayList<Source>> {
    private MainActivity activity;



    public GetDataJSON(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected ArrayList<Source> doInBackground(String... strings) {
        ArrayList<Source> apps = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            }
        } catch (Exception e) {
            //Handle the exceptions
        } finally {
            //Close open connections and reader
        }

        try {
            JSONObject root = new JSONObject(result);
            JSONArray results = root.getJSONArray("sources");

            for (int i = 0; i < results.length(); i++) {
                JSONObject resultSource = results.getJSONObject(i);

                String id = resultSource.getString("id");
                String name = resultSource.getString("name");

                apps.add(new Source(id, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return apps;
    }

    @Override
    protected void onPostExecute(ArrayList<Source> result) {
        super.onPostExecute(result);

        activity.handleData(result);
    }
}
