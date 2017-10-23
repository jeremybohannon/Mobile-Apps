package com.example.android.a800853379_midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jeremybohannon on 10/16/17.
 */


//Elizabeth Thompson Jeremy bohannon
//In class 06
//Custom article adapter
public class CustomArticleAdapter extends ArrayAdapter<Article> {
    private List<Article> objects;

    public CustomArticleAdapter(Context context, int resource, List<Article> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ArticleViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.article_layout, parent, false);

            viewHolder = new ArticleViewHolder();

            viewHolder.title = view.findViewById(R.id.newsTitle);
            viewHolder.author = view.findViewById(R.id.author);
            viewHolder.publishedAt = view.findViewById(R.id.publishedAt);
            viewHolder.image = view.findViewById(R.id.image);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ArticleViewHolder) view.getTag();
        }

        Article object = getItem(position);

        viewHolder.title.setText(object.getTitle());
        viewHolder.author.setText(object.getAuthor());
        viewHolder.publishedAt.setText(object.getPublishedAt());

        Picasso.with(this.getContext()).load(object.getUrlToImage()).into(viewHolder.image);

        return view;
    }
}
