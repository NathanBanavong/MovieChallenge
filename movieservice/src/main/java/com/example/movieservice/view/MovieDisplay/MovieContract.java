package com.example.movieservice.view.MovieDisplay;

import com.example.movieservice.data.Movies;
import com.example.movieservice.view.BaseView;

import java.util.List;

import io.reactivex.Single;

public interface MovieContract {
    interface View extends BaseView {

        void onListUpdated(List<Movies> items);

    }


    interface Presenter extends BasePresenter<View> {

        //        void updateList(String strSearch);
        Single<List<Movies>> MovieSearch(String input, Integer pageNum);

    }
}
