package com.martynov.newsapp.net;

import com.martynov.newsapp.models.Category;
import com.martynov.newsapp.models.Country;
import com.martynov.newsapp.models.Language;
import com.martynov.newsapp.models.NewsResponse;
import com.martynov.newsapp.models.SourceResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by mihai on 3/8/2018.
 */

public interface NewsApiService {
    //baseline https://newsapi.org/
//https://newsapi.org/v2/everything?q=bitcoin&apiKey=0301238606e64c3cbe7c5de0cd5ac143
    @GET("/v2/everything")
    Call<NewsResponse> everything(@Query("apiKey") String apiKey,
                                  @QueryMap(encoded = true) Map<String, String> params);

    @GET("/v2/top-headlines")
    Call<NewsResponse> topHeadlines(@Query("apiKey") String apiKey,
                                    @Query("country") Country country,
                                    @Query("category") Category category);


    @GET("/v2/sources")
    Call<SourceResponse> sources(@Query("apiKey") String apiKey,
                                 @Query("category") Category category,
                                 @Query("language") Language language,
                                 @Query("country") Country country);
}

///v2/everything?{params}&apiKey=0301238606e64c3cbe7c5de0cd5ac143
