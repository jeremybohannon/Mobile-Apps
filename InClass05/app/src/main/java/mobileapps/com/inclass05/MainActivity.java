package mobileapps.com.inclass05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    MultiAutoCompleteTextView keyword;
    private static final String[] KEYWORDS = new String[] {};

    Button goBtn;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Connection().execute("http://dev.theappsdr.com/apis/photos/keywords.php");

        goBtn = (Button) findViewById(R.id.goBtn);
        photo = (ImageView) findViewById(R.id.photo);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetImageAsync(photo).execute();
            }
        });



    }

    private class Connection extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
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

            // setup the autocomplete for keyword search
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, KEYWORDS);
            keyword = (MultiAutoCompleteTextView) findViewById(R.id.keyword);
            keyword.setAdapter(adapter);
            keyword.setThreshold(1);
            keyword.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        }
    }

    private class GetImageAsync extends AsyncTask<String, Void, Void> {
        ImageView imageView;
        Bitmap bitmap = null;

        public GetImageAsync(ImageView iv) {
            imageView = iv;
        }

        @Override
        protected Void doInBackground(String... params) {
            HttpURLConnection connection = null;
            bitmap = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                }
            } catch (Exception e) {
                //Handle the exceptions
            } finally {
                //Close open connection
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (bitmap != null && imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

}
