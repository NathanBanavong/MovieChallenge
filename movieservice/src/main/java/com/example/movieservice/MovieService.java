package com.example.movieservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.movieservice.data.MovieAPI;

import java.io.Serializable;

public class MovieService extends Service {

    public static final String TAG = MovieService.class.getSimpleName() + "_TAG";
    MovieAPI api;

    public MovieService() {
        Log.d(TAG, "MovieService: ");
        api = new MovieAPI(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //    reflected on the AIDL
    private final MovieServiceAIDL.Stub mBinder = new MovieServiceAIDL.Stub() {
        @Override
        public String ping() throws RemoteException {
            Log.d(TAG, "ping: ");
            return "ding-dong";
        }

        @Override
        public Bundle search(String leQuery, int pageNum) throws RemoteException {
            Bundle ret = new Bundle();
            Log.d(TAG, "search: " + ret.toString());
            try {
                ret.putSerializable("data", (Serializable) api.search(leQuery, pageNum));
                return ret;
            } catch (Exception e) {
                Log.d(TAG, "search: " + e.getMessage());
                return ret;
            }
        }
    };
}
