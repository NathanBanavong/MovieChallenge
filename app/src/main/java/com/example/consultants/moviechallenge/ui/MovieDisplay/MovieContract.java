package com.example.consultants.moviechallenge.ui.MovieDisplay;

import com.example.consultants.moviechallenge.data.repository.MovieDB;
import com.example.consultants.moviechallenge.ui.BasePresenter;
import com.example.consultants.moviechallenge.ui.BaseView;

import java.util.List;

import io.reactivex.Single;

public interface MovieContract {
    interface View extends BaseView {


//        void onListUpdated(List<Repository> items);

    }


    interface Presenter extends BasePresenter<View> {

//        void updateList(String strSearch);
        Single<List<MovieDB>>MovieSearch(String input, Integer pageNum);

    }
}
