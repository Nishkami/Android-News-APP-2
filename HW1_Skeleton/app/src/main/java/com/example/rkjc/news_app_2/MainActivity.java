package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;


import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private ArrayList<NewsItem> newsItemArrayList;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mRecycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsItemArrayList=new ArrayList<NewsItem>();

        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRecycleAdapter=new NewsRecyclerViewAdapter(this,newsItemArrayList);

        mRecyclerView.setAdapter(mRecycleAdapter);

        new NewsQueryTask().execute();

    }



    public class NewsQueryTask extends AsyncTask<Void, Void,ArrayList<NewsItem>> {

        @Override
        protected  ArrayList<NewsItem> doInBackground(Void...params) {

            URL newsUrl = NetworkUtils.buildUrl();

            try {
                String jsonNewsResponse = NetworkUtils
                        .getResponseFromHttpUrl(newsUrl);

                ArrayList<NewsItem> newsItemArrayList = JsonUtils.parseNews(jsonNewsResponse);
                System.out.print(newsItemArrayList+"\n");
                return  newsItemArrayList;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<NewsItem> newsItemArrayList) {

            if (newsItemArrayList!= null) {
                mRecycleAdapter = new NewsRecyclerViewAdapter(MainActivity.this, newsItemArrayList);
                mRecyclerView.setAdapter(mRecycleAdapter);
                mRecycleAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(NewsItem item) {

                        Uri webpage = Uri.parse(item.getUrl());

                        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.get_news) {
            mRecycleAdapter.setNewsData(null);
            new NewsQueryTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
