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

/**
 * Created by jeremybohannon on 10/16/17.
 */

public class GetArticleDataJSON extends AsyncTask<String, Void, ArrayList<Article>> {
    private NewsActivity activity;


    public GetArticleDataJSON(NewsActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Article> doInBackground(String... strings) {
        ArrayList<Article> articles = new ArrayList<>();
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
            JSONArray results = root.getJSONArray("articles");

            for (int i = 0; i < results.length(); i++) {
                JSONObject resultSource = results.getJSONObject(i);

                String author = resultSource.getString("author");
                String title = resultSource.getString("title");
                String url = resultSource.getString("url");
                String urlToImage = resultSource.getString("urlToImage");
                String publishedAt = resultSource.getString("publishedAt");

                articles.add(new Article(author, title, url, urlToImage, publishedAt));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articles;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> result) {
        super.onPostExecute(result);

        activity.handleData(result);
    }
}