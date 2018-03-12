package com.martynov.newsapp.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mihai on 3/8/2018.
 */

public class NewsServiceCreator {

    public static final String API_KEY = "0301238606e64c3cbe7c5de0cd5ac143";

    private static final NewsServiceCreator mInstance = new NewsServiceCreator();

    private Retrofit.Builder builder;

    private NewsServiceCreator() {
        builder = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static NewsServiceCreator getInstance() {
        return mInstance;
    }

    public <T> T createService(final Class<T> serviceClass) {
        return builder.build().create(serviceClass);
    }

    protected Retrofit.Builder getBuilder() {
        return builder;
    }

}
