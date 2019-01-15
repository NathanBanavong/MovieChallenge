package com.example.movieservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.movieservice.data.MovieAPI;
import com.example.movieservice.data.Movies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieAIDLService extends Service {

    MovieAPI api;
    List<Movies> movieList = new ArrayList<>();

    public MovieAIDLService(String userInput, Integer pageNum)
            throws ExecutionException, InterruptedException {
        movieList = api.search(userInput, pageNum);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final MovieServiceAIDL.Stub mBinder = new MovieServiceAIDL.Stub() {
        @Override
        public String ping() throws RemoteException {
            return "pong";
        }

        @Override
        public Bundle search(String aQuery, int pageNum) throws RemoteException {
            Bundle ret = new Bundle();

            try {
                ret.putSerializable("data", (Serializable) api.search(aQuery, pageNum));
                return ret;
            } catch (Exception e) {
                System.out.println("__TAG__ " + e.getMessage());
                return ret;
            }
        }
    };
}
