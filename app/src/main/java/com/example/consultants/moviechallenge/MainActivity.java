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

    private RequestQueue queue;
    private EditText etSearch;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBindView();

    }

    public void onBindView() {
        etSearch = findViewById(R.id.etSearch);
    }

    public void onSearch(View view) {

        queue = Volley.newRequestQueue(this);
        url = NetworkHelper.SEARCH_URL + etSearch.getText();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                    mTextView.setText("Response is: " + response.substring(0, 500));
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    //    --------------------------------------------------------------------
//    RequestQueue queue = Volley.newRequestQueue(this);
//    String url = NetworkHelper.URL;
//
//    // Request a string response from the provided URL.
//    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    // Display the first 500 characters of the response string.
////                    mTextView.setText("Response is: " + response.substring(0, 500));
//                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Toast.makeText(getApplicationContext(), error.toString(),
//                    Toast.LENGTH_LONG).show();
//        }
//    });
//
//// Add the request to the RequestQueue.
//queue.add(stringRequest);
//    --------------------------------------------------------------------

}
