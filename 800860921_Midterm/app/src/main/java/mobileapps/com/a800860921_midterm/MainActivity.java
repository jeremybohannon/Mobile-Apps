package mobileapps.com.a800860921_midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private LinearLayout sourcesLayout;
    private ArrayList<Source> sources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isConnected()) {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Loading sources . . .");
            dialog.show();
            new GetDataAsync().execute("https://newsapi.org/v1/sources");
        } else {
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener onSourceClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            Source source = sources.get(textView.getId());

            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            intent.putExtra("id", source.getId());
            intent.putExtra("name", source.getName());
            startActivity(intent);
        }
    };

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

    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<Source>> {
        @Override
        protected ArrayList<Source> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Source> result = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");

                    JSONObject root = new JSONObject(json);
                    JSONArray sources = root.getJSONArray("sources");

                    for (int i = 0; i < sources.length(); i++) {
                        JSONObject sourceJson = sources.getJSONObject(i);
                        String id = sourceJson.getString("id");
                        String name = sourceJson.getString("name");
                        Source source = new Source(id, name);
                        result.add(source);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Source> result) {
            if (result.size() > 0) {
                sources = result;
                sourcesLayout = (LinearLayout) findViewById(R.id.sourcesLayout);

                for (int i = 0; i < result.size(); i++) {
                    TextView text = new TextView(MainActivity.this);

                    text.setText(result.get(i).getName());
                    text.setPadding(20, 40, 20, 40);
                    text.setOnClickListener(onSourceClick);
                    text.setId(i);

                    sourcesLayout.addView(text);
                }
            } else {
                Log.d("demo", "empty result");
            }

            dialog.hide();
        }
    }
}
