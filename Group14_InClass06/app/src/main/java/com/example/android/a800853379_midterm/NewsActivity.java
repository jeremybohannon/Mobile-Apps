package com.example.android.a800853379_midterm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//In class 06
//Jeremy Bohannon Elizabeth Thompson
//News activity
public class NewsActivity extends AppCompatActivity {

    ProgressDialog progress;
    Source source;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progress = ProgressDialog.show(this, "Loading...",
                "", true);

        source = (Source) getIntent().getSerializableExtra("Source_Object");

        String URL = "https://newsapi.org/v1/articles?source=" + source.getId() + "&apiKey=2129ea92f78b40ef95eadcf6cd586025";
        new GetArticleDataJSON(this).execute(URL);

        setTitle(source.getName());
    }

    public void handleData(ArrayList<Article> articles){
        setUpListViewArticle(articles);
    }

    public void setUpListViewArticle(final ArrayList<Article> articles){
        System.out.println("In set up list view article");
        lv = (ListView) findViewById(R.id.listView);

        final CustomArticleAdapter customAdapter = new CustomArticleAdapter(this, R.layout.article_layout, articles);
        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                Article article = articles.get(i);

                System.out.println("Object: " + article.getAuthor());

                String url = article.getUrl();

                WebView webview =  new WebView(NewsActivity.this);
                setContentView(webview);
                webview.loadUrl(url);

            }
        });

        progress.dismiss();

    }
}
