package com.example.consultants.moviechallenge.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.consultants.moviechallenge.data.repository.MovieDB;
import com.example.consultants.moviechallenge.utils.NetworkHelper;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieAPI {

    public static final String TAG = MovieAPI.class.getSimpleName() + "_TAG";
    private Context context;

    public MovieAPI(Context context) {
        this.context = context;
    }

    public List<MovieDB> search(String query, Integer pageNum) throws ExecutionException, InterruptedException {
        Log.d(TAG, "search: " + query);
        RequestFuture<String> future = RequestFuture.newFuture();
        String url = NetworkHelper.BASE_URL +
                "?api_key=" + NetworkHelper.API_KEY +
                "&language=en-US&query=" + query +
                "&page=" + pageNum + "";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, future, future);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);

        String response = future.get();
        MovieResult result = new Gson().fromJson(response, MovieResult.class);
        return result.getResults();
    }

}
