package com.dmitryk.reddit.rest;

import com.dmitryk.reddit.rest.model.Post;
import com.dmitryk.reddit.rest.service.RedditService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditClient {

    private static RedditClient instance;

    private int VALUE_DEFAULT_TIME_OUT = 20 * 1000;
    private static final String BASE_URL = "https://www.reddit.com/";

    private OkHttpClient.Builder okHttpClient;
    private RedditService api;
    private Retrofit retrofit;


    private RedditClient() {
        initRetrofit();
    }

    private void initRetrofit() {

        PostDeserializer<Post> myDeserializer = new PostDeserializer<>();


        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .registerTypeAdapter(Post.class, myDeserializer)
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(logging);


        okHttpClient.connectTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(VALUE_DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS);
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(RedditService.class);
    }

    public static RedditClient getInstance() {
        if (instance == null) {
            instance = new RedditClient();
        }
        return instance;
    }

    public RedditService getApi() {
        return api;
    }
}
