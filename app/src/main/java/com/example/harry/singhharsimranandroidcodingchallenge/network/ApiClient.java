package com.example.harry.singhharsimranandroidcodingchallenge.network;

import com.example.harry.singhharsimranandroidcodingchallenge.BuildConfig;
import com.example.harry.singhharsimranandroidcodingchallenge.model.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by harry on 10/12/17.
 */

public class ApiClient {

    private static final String BASE_URL="http://de-coding-test.s3.amazonaws.com";
    private static Retrofit retrofit = null;
    private static boolean shouldRebuild = false;

    public static Retrofit sharedClient() {
        if (retrofit==null || shouldRebuild ) {
            shouldRebuild = false;
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectTimeout(5, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }
            builder.addInterceptor(new Interceptor() {
                @Override public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            OkHttpClient client = builder.build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd @ HH:mm")
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    private static final ApiInterface API_INTERFACE = sharedClient().create(ApiInterface.class);

    public static ApiInterface apiInterface() {
        return API_INTERFACE;
    }

    public interface ApiInterface {

        @GET("/books.json")
        Call<JsonResponse> getJsonData();
    }
}
