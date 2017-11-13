package mobileapps.com.a800860921_midterm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private LinearLayout articlesLayout;
    private final String API_KEY = "e700460bede347c3a7e20bdaba8fb6eb";
    private ArrayList<Article> articles = new ArrayList<>();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (getIntent() != null && getIntent().getExtras() != null) {
            String id = getIntent().getStringExtra("id");
            String name = getIntent().getStringExtra("name");

            setTitle(name);

            dialog = new ProgressDialog(NewsActivity.this);
            dialog.setTitle("Loading stories . . .");
            dialog.show();
            new NewsActivity.GetDataAsync().execute("https://newsapi.org/v1/articles?source=" + id + "&apiKey=" + API_KEY);
        }
    }

    View.OnClickListener onArticleClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String url = articles.get(view.getId()).getUrl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    };

    private class GetDataAsync extends AsyncTask<String, Void, ArrayList<Article>> {
        @Override
        protected ArrayList<Article> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Article> result = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");

                    JSONObject root = new JSONObject(json);
                    JSONArray articles = root.getJSONArray("articles");

                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject articleJson = articles.getJSONObject(i);
                        String author = articleJson.getString("author");
                        String title = articleJson.getString("title");
                        String articleUrl = articleJson.getString("url");
                        String urlToImage = articleJson.getString("urlToImage");
                        String publishedAt = articleJson.getString("publishedAt");
                        Article article = new Article(author, title, articleUrl, urlToImage, publishedAt);
                        result.add(article);
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
        protected void onPostExecute(ArrayList<Article> result) {
            dialog.hide();

            if (result.size() > 0) {
                articles = result;
                articlesLayout = (LinearLayout) findViewById(R.id.articlesLayout);

                for (int i = 0; i < result.size(); i++) {
                    Log.d("demo", result.get(i).toString());

                    imageView = new ImageView(NewsActivity.this);
                    TextView titleView = new TextView(NewsActivity.this);
                    TextView authorView = new TextView(NewsActivity.this);
                    TextView dateView = new TextView(NewsActivity.this);

                    LinearLayout container = new LinearLayout(NewsActivity.this);
                    LinearLayout row1 = new LinearLayout(NewsActivity.this);
                    LinearLayout row2 = new LinearLayout(NewsActivity.this);

                    container.setOrientation(LinearLayout.VERTICAL);
                    row1.setOrientation(LinearLayout.HORIZONTAL);
                    row2.setOrientation(LinearLayout.HORIZONTAL);

                    new GetImageAsync(imageView).execute(result.get(i).getUrlToImage());
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxWidth(200);
                    titleView.setText(result.get(i).getTitle());
                    row1.addView(imageView);
                    row1.addView(titleView);

                    String author = (result.get(i).getAuthor() == "null" ? "Author unknown" : result.get(i).getAuthor());
                    authorView.setText(author);
                    dateView.setText(result.get(i).getPublishedAt());

                    int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
                    authorView.setWidth(width / 2);
                    dateView.setWidth(width / 2);
                    dateView.setGravity(Gravity.RIGHT);

                    row2.addView(authorView);
                    row2.addView(dateView);

                    container.addView(row1);
                    container.addView(row2);

                    container.setId(i);
                    container.setPadding(20, 40, 20, 40);
                    container.setOnClickListener(onArticleClick);

                    articlesLayout.addView(container);
                }
            } else {
                Log.d("demo", "empty result");
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
            HttpURLConnection connection = null;
            bitmap = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
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
