package com.example.consultants.moviechallenge.ui.MovieDisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.consultants.moviechallenge.R;
import com.example.consultants.moviechallenge.data.repository.MovieDB;
import com.example.consultants.moviechallenge.ui.adapters.MovieAdapter;
import com.example.consultants.moviechallenge.utils.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    private EditText etSearch;
    private MoviePresenter presenter;
    private RecyclerView rvMovieList;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBindView();

    }

    public void onBindView() {

        etSearch = findViewById(R.id.etSearch);

        presenter = MoviePresenter.getInstance(this);
        rvMovieList = findViewById(R.id.rvSearchList);
        adapter = new MovieAdapter(new ArrayList<MovieDB>());
        rvMovieList.setAdapter(adapter);
        rvMovieList.setLayoutManager(new LinearLayoutManager(this));
    }

//    TODO issues with the 'Single' onSubscribe
    public void onSearch(View view) {
        presenter.MovieSearch(etSearch.getText().toString(),
                1)
                .subscribe(new SingleObserver<List<MovieDB>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<MovieDB> movies) {
                        // Populate movies
                        Log.d(TAG, "onSuccess: " + movies.size());
                        adapter.setMovies(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
