package com.example.movieservice.view.MovieDisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.movieservice.R;
import com.example.movieservice.data.Movies;
import com.example.movieservice.view.Adapter.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    private android.support.v7.widget.SearchView svUserInput;
    private SearchView searchView;

    private MoviePresenter presenter;
    private RecyclerView rvMovieList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBindView();

        svUserInput = findViewById(R.id.svUserInput);
        svUserInput.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: " + s);
//                search();
                TrySearch();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: " + s);
//                search();
                TrySearch();
                return true;
            }
        });

    }

    public void onBindView() {
        Log.d(TAG, "onBindView: ");

        presenter = MoviePresenter.getInstance(this);
        rvMovieList = findViewById(R.id.rvSearchList);

        adapter = new MovieAdapter(new ArrayList<Movies>());

        rvMovieList.setAdapter(adapter);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void search() {
        Log.d(TAG, "search: ");
        presenter.MovieSearch(svUserInput.getQuery().toString(), 1)
//                TODO Subscribe is returning a null object reference
                .subscribe(new SingleObserver<List<Movies>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: " + d.toString());
                    }

                    //                    TODO still skipping 'onSuccess' - returning a null object reference
                    @Override
                    public void onSuccess(List<Movies> movies) {
                        // Populate movies
                        Log.d(TAG, "onSuccess: " + movies.size());
                        adapter.setMovies(movies);
                    }

                    //                    TODO ISSUE IS HERE!!
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    //    TODO WTF is '.subscribe((Consumer<? super....getSearchedItem))'
    private void TrySearch() {
        Log.d(TAG, "TrySearch: ");
//        Single.just(presenter.MovieSearch(svUserInput.getQuery().toString(), 1))
//                .subscribe((BiConsumer<? super Single<List<Movies>>, ? super Throwable>) getSearchedItem());
        presenter.SearchMovie(svUserInput.getQuery().toString(), 1).subscribe(getMovieList());

    }

    private Observer<List<Movies>> getMovieList() {
        return new Observer<List<Movies>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: " + d);
            }

            @Override
            public void onNext(List<Movies> movies) {
                Log.d(TAG, "onNext: " + movies.size());
                adapter.setMovies(movies);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
    }


    private SingleObserver<List<Movies>> getSearchedItem() {
        return new SingleObserver<List<Movies>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(List<Movies> movies) {
                Log.d(TAG, "onSuccess: " + movies.size());
                adapter.setMovies(movies);
            }


            @Override
            public void onError(Throwable e) {
//                textView.append(" onError : " + e.getMessage());
//                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }

    public void onListUpdated(List<Movies> movieList) {
        Log.d(TAG, "onListUpdated: ");

        adapter = new MovieAdapter(movieList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setItemAnimator(itemAnimator);
        rvMovieList.setAdapter(adapter);
    }

}
