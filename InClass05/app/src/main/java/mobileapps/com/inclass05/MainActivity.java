package mobileapps.com.inclass05;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//In class 5
//MainActivity.java
//Elizabeth Thompson Jeremy Bohannon

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView keyword;
    private static String[] KEYWORDS = new String[]{};
    private static String[] IMAGES = new String[]{};

    ProgressDialog progress;
    Button goBtn;
    ImageView photo, prevBtn, nextBtn;
    String currentKeyword;
    int keywordsAmount = 0;
    int currentKeywordIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = new ProgressDialog(MainActivity.this);

        keyword = (AutoCompleteTextView) findViewById(R.id.keyword);
        keyword.setEnabled(false);

        if (isConnected()) {
            progress.setTitle("Loading...");
            progress.show();
            new Connection(false).execute("http://dev.theappsdr.com/apis/photos/keywords.php?type=json");
        } else {
            goBtn.setEnabled(false);
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

        goBtn = (Button) findViewById(R.id.goBtn);
        photo = (ImageView) findViewById(R.id.photo);
        prevBtn = (ImageView) findViewById(R.id.prevBtn);
        nextBtn = (ImageView) findViewById(R.id.nexBtn);

        prevBtn.setEnabled(false);
        nextBtn.setEnabled(false);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setTitle("Loading images...");
                progress.show();
                if (currentKeywordIndex > 0) {
                    currentKeywordIndex--;
                    new GetImageAsync(photo).execute(IMAGES[currentKeywordIndex]);
                } else if (currentKeywordIndex == 0) {
                    currentKeywordIndex = keywordsAmount;
                    new GetImageAsync(photo).execute(IMAGES[currentKeywordIndex]);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setTitle("Loading images...");
                progress.show();
                System.out.println(currentKeywordIndex);
                if (currentKeywordIndex < keywordsAmount) {
                    currentKeywordIndex++;
                    new GetImageAsync(photo).execute(IMAGES[currentKeywordIndex]);
                } else if (currentKeywordIndex == keywordsAmount) {
                    currentKeywordIndex = 0;
                    new GetImageAsync(photo).execute(IMAGES[currentKeywordIndex]);
                }
            }
        });

        keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoCompleteTextView keyword = (AutoCompleteTextView) view;
                keyword.showDropDown();
            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentKeyword = keyword.getText().toString();
                progress.setTitle("Loading images...");
                progress.show();
                prevBtn.setEnabled(false);
                nextBtn.setEnabled(false);
                new Connection(true).execute("http://dev.theappsdr.com/apis/photos/index.php?type=json&keyword=" + currentKeyword);
            }
        });


    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }


    private class Connection extends AsyncTask<String, Void, String> {
        Boolean isImageRequest;

        Connection(Boolean isImageRequest) {
            this.isImageRequest = isImageRequest;
        }

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
            }

            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (isImageRequest) {
                System.out.println("\n\n\n\n Result: " + result);

                try {
                    JSONObject root = null;
                    root = new JSONObject(result);
                    JSONArray images = root.getJSONArray("urls");

                    IMAGES = new String[images.length()];
                    for (int i = 0; i < images.length(); i++) {
                        IMAGES[i] = images.get(i).toString();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                keywordsAmount = IMAGES.length - 1;
                currentKeywordIndex = 0;
                if (result.length() == 0) {
                    photo.setImageResource(R.drawable.picture);
                    Toast.makeText(MainActivity.this, "No images found", Toast.LENGTH_SHORT).show();
                    progress.cancel();
                } else {
                    new GetImageAsync(photo).execute(IMAGES[currentKeywordIndex]);
                    if (keywordsAmount == 1) {
                        nextBtn.setEnabled(false);
                        prevBtn.setEnabled(false);
                    } else {
                        nextBtn.setEnabled(true);
                        prevBtn.setEnabled(true);
                    }
                }
            } else {
                // setup the autocomplete for keyword search
                try {
                    JSONObject root = null;
                    root = new JSONObject(result);
                    JSONArray keywords = root.getJSONArray("categories");

                    KEYWORDS = new String[keywords.length()];
                    for (int i = 0; i < keywords.length(); i++) {
                        KEYWORDS[i] = keywords.get(i).toString();
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, KEYWORDS);
                    keyword.setAdapter(adapter);
                    keyword.setEnabled(true);

                    progress.cancel();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
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
            HttpURLConnection connection;
            bitmap = null;
            try {
                System.out.println("\n\n\n\n\n\n response: \t\t\t" + params[0]);
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
            progress.cancel();
            if (bitmap != null && imageView != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(MainActivity.this, "Error in loading image...", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
