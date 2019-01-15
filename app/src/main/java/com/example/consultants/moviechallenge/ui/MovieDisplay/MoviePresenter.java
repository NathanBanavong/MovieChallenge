package com.example.consultants.moviechallenge.ui.MovieDisplay;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

//import com.example.movieservice.MovieService;
//import com.example.movieservice.MovieServiceAIDL;
//import com.example.movieservice.data.repository.MovieDB;


import com.example.consultants.moviechallenge.data.repository.MovieDB;
import com.example.consultants.moviechallenge.utils.MovieServiceAIDL;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter implements MovieContract.Presenter {

    public static final String TAG = MoviePresenter.class.getSimpleName() + "_TAG";
    private MovieContract.View view;
    private static MoviePresenter lePresenter;
    private MovieServiceAIDL movieServiceAIDL;
    private Boolean checkConnected = true;
    private Integer pageNum;
    private String userInput;

    private ServiceConnection mConnection = new ServiceConnection() {

        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG, "onServiceConnected: " + className.toString());
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            movieServiceAIDL = MovieServiceAIDL.Stub.asInterface(service);
            checkConnected = true;
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "onServiceDisconnected: " + className.toString());
            movieServiceAIDL = null;
            checkConnected = false;
        }
    };

    public MoviePresenter(Context context) {
        Log.d(TAG, "MoviePresenter: " + context.toString());
//        TODO not referencing the 'MovieService.class' - using stub
        Intent intent = new Intent(context, MovieServiceAIDL.Stub.class);
        intent.setAction(MovieServiceAIDL.class.getName());
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public static MoviePresenter getInstance(Context context) {
        Log.d(TAG, "getInstance: " + context.toString());
        if (lePresenter == null) {
            Log.d(TAG, "getInstance: ");
            lePresenter = new MoviePresenter(context);
        }
        return lePresenter;
    }

//    public Single<List<MovieDB>> search(final String input, final Integer pageNum) {
//        if (!checkConnected) return null;
//
//        return Single.fromCallable(new Callable<List<MovieDB>>() {
//            @Override
//            public List<MovieDB> call() throws Exception {
//                Bundle b = movieServiceAIDL.search(input, pageNum);
//                return (List<MovieDB>) b.getSerializable("data");
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public Single<List<MovieDB>> MovieSearch(final String input, final Integer pageNum) {
        Log.d(TAG, "MovieSearch: " + input + "," + pageNum.toString());
//        if not connected will have issue
        if (!checkConnected) return null;

        this.pageNum = pageNum;
        userInput = input;

//        return Single.fromCallable(new Callable<List<MovieDB>>() {
//            @Override
//            public List<MovieDB> call() throws Exception {
//                Bundle b = movieServiceAIDL.search(input, pageNum);
////                TODO passed into the MainActivity - check
////                view.onListUpdated((List<MovieDB>) b.getSerializable("Data"));
////                ---------------------------------------------------------------
//                return (List<MovieDB>) b.getSerializable("data");
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());

        return lazyLoad();
    }

    public Single<List<MovieDB>> lazyLoad() {
        Log.d(TAG, "lazyLoad: ");
        pageNum++;
        return Single.fromCallable(new Callable<List<MovieDB>>() {
            @Override
            public List<MovieDB> call() throws Exception {
                Bundle b = movieServiceAIDL.search(userInput, pageNum);
                Log.d(TAG, "call: ");
                return (List<MovieDB>) b.getSerializable("data");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void attachView(MovieContract.View view) {
        Log.d(TAG, "attachView: ");
    }

    @Override
    public void removeView() {
        Log.d(TAG, "removeView: ");
    }
}
