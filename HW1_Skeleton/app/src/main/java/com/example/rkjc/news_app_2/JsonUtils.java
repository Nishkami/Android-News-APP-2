package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.*;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String JSONString) throws JSONException {

        JSONObject object=new JSONObject(JSONString);

        JSONArray newsArray=object.getJSONArray("articles");

        ArrayList<NewsItem> list=new ArrayList<>();

        for(int i=0;i<newsArray.length();i++){

            NewsItem newsItem=new NewsItem();

            JSONObject news=newsArray.getJSONObject(i);

            newsItem.setAuthor(news.getString("author"));
            newsItem.setTitle(news.getString("title"));
            newsItem.setDescription(news.getString("description"));
            newsItem.setUrl(news.getString("url"));
            newsItem.setUrlToImage(news.getString("urlToImage"));
            newsItem.setPublishedAt(news.getString("publishedAt"));
            list.add(newsItem);
        }

        return list;
    }
}

