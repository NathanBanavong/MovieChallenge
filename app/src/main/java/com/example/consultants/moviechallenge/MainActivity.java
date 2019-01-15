package com.example.consultants.moviechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.consultants.moviechallenge.utils.NetworkHelper;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
//    private MoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBindView();

    }

    public void onBindView() {

//        etSearch = findViewById(R.id.etSearch);
//
//        presenter = MainPresenter.getInstance(this);
//        rvMovieList = findViewById( R.id.rvMovieList );
//        adapter = new MovieAdapter(new ArrayList<Movie>());
//        rvMovieList.setAdapter(adapter);
//        rvMovieList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onSearch(View view) {

    }

}
