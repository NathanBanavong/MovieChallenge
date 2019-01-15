package com.example.consultants.moviechallenge.ui.MovieDisplay;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.ViewDebug;

import com.example.consultants.moviechallenge.data.MovieAPI;
import com.example.consultants.moviechallenge.data.repository.MovieDB;

import java.io.Serializable;

public class MovieService extends Service {

    public static final String TAG = MovieService.class.getCanonicalName() + "_TAG";
    MovieAPI api;

    public MovieService() {
        api = new MovieAPI(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final MovieServiceAIDL.Stub mBinder = new MovieServiceAIDL.Stub() {
        @Override
        public String ping() throws RemoteException {
            Log.d(TAG, "ping: ");
            return "pong";
        }

        @Override
        public Bundle search(String aQuery, int pageNum) throws RemoteException {
            Bundle ret = new Bundle();
            Log.d(TAG, "search: " + aQuery + ", " + pageNum);
            try {
                Log.d(TAG, "search: ");
                ret.putSerializable("data", (Serializable) api.search(aQuery, pageNum));
                return ret;
            } catch (Exception e) {
                Log.d(TAG, "search: " + e.toString());
                System.out.println("__TAG__ " + e.getMessage());
                return ret;
            }
        }
    };
}
