package com.example.consultants.moviechallenge.ui;

public interface BasePresenter <V extends BaseView> {
    void attachView(V view);
    void removeView();
}
