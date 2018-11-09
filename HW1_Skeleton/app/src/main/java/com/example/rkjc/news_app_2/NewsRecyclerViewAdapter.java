package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {


    ArrayList<NewsItem> newsdatalist;
    Context mcontext;
    private OnItemClickListener onItemClickListener;

    public NewsRecyclerViewAdapter(Context context,ArrayList<NewsItem> newsdatalist){
        this.mcontext=context;
        this.newsdatalist=newsdatalist;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new NewsViewHolder(view);
    }


    public void onBindViewHolder(NewsViewHolder newsViewHolder, int i) {
        final NewsItem newsitem = newsdatalist.get(i);

        newsViewHolder.titleView.setText("Title: " + newsitem.getTitle());
        newsViewHolder.descriptionView.setText("Description: " + newsitem.getDescription());
        newsViewHolder.dateView.setText("Date: " + newsitem.getPublishedAt());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(newsitem);
            }
        };
        newsViewHolder.titleView.setOnClickListener(listener);
        newsViewHolder.descriptionView.setOnClickListener(listener);
        newsViewHolder.dateView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if (null == newsdatalist) return 0;
        return newsdatalist.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleView;
        public final TextView descriptionView;
        public final TextView dateView;

        public NewsViewHolder(View view){
            super(view);
            titleView=(TextView)view.findViewById(R.id.title_t);
            descriptionView=(TextView)view.findViewById(R.id.description_t);
            dateView=(TextView)view.findViewById(R.id.date_t);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setNewsData(ArrayList<NewsItem> newsdatalist) {
        this.newsdatalist=newsdatalist;
        notifyDataSetChanged();
    }

}
