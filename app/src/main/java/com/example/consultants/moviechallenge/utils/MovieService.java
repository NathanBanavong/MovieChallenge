package com.example.consultants.moviechallenge.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.consultants.moviechallenge.data.MovieAPI;

import java.io.Serializable;

public class MovieService extends Service {

    public static final String TAG = MovieService.class.getSimpleName() + "_TAG";
    MovieAPI api;

    public MovieService() {
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
            return "ding-dong";
        }

        @Override
        public Bundle search(String leQuery, int pageNum) throws RemoteException {
            Bundle ret = new Bundle();

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
