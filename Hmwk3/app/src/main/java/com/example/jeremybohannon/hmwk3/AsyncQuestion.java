package com.example.jeremybohannon.hmwk3;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncQuestion extends AsyncTask<String, Void, String> {
    MainActivity activity;

    public AsyncQuestion(MainActivity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {
        System.out.println("[ MainActivity | doInBackground ] In async task");
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("[ AsyncQuestions | doInBackground ] Got response... making string..");
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            //Close open connections and reader
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        activity.setQuestions(result);
    }
}
