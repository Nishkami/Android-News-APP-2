package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import android.util.Log;

public class NetworkUtils {
    final static String TAG = NetworkUtils.class.getSimpleName();
    final static String BASE_URL = "https://newsapi.org/v2/everything?q=bitcoin&from=2018-10-05&sortBy=publishedAt&apiKey=71f65f7b6de84822a511758080818f5e";
    final static String API_KEY = "71f65f7b6de84822a511758080818f5e";
    public static URL buildUrl(String location) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY, location )
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
