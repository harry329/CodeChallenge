package com.example.harry.singhharsimranandroidcodingchallenge.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.harry.singhharsimranandroidcodingchallenge.R;
import com.example.harry.singhharsimranandroidcodingchallenge.adapter.BookAdapter;
import com.example.harry.singhharsimranandroidcodingchallenge.model.JsonResponse;
import com.example.harry.singhharsimranandroidcodingchallenge.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mRecyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvBooks);
        mRecyclerLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecyclerLayoutManager);
    }

    public void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        Call<JsonResponse> call =  ApiClient.apiInterface().getJsonData();
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                setVisible(false);
                if(response.body() != null) {
                    Log.d("response", String.valueOf(response.body()));
                    BookAdapter bookAdapter = new BookAdapter(response.body(),getBaseContext());
                    mRecyclerView.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}
