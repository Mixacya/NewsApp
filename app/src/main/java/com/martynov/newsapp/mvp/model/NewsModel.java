package com.martynov.newsapp.mvp.model;

import android.support.annotation.NonNull;

import com.martynov.newsapp.models.Category;
import com.martynov.newsapp.models.Country;
import com.martynov.newsapp.models.NewsResponse;
import com.martynov.newsapp.models.NewsRequest;
import com.martynov.newsapp.models.SortBy;
import com.martynov.newsapp.mvp.presenter.NewsPresenter;
import com.martynov.newsapp.net.NewsApiService;
import com.martynov.newsapp.net.NewsServiceCreator;
import com.martynov.newsapp.utils.Logger;
import com.martynov.newsapp.utils.Utils;

import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mihai on 3/9/2018.
 */

public class NewsModel extends BaseNewsModel {

    private static final String TAG = "NewsModel";

    private NewsApiService mNewsApiService;

    public NewsModel(@NonNull final NewsPresenter presenter) {
        super(presenter);
        mNewsApiService = NewsServiceCreator.getInstance().createService(NewsApiService.class);
    }

    @Override
    public void startReading() {
        final NewsRequest lastRequest = getLastRequest();
        final Country country = lastRequest.getCountry() != null
                ? lastRequest.getCountry()
                : Utils.getCurrentCountry();

        final Category category = lastRequest.getCategory();
        getPresenter().onStartReading();
        final Call<NewsResponse> call = lastRequest.hasAtLeastOneRequiredField()
                ? mNewsApiService.everything(NewsServiceCreator.API_KEY, lastRequest.toMap())
                : mNewsApiService.topHeadlines(NewsServiceCreator.API_KEY, country, category);
        //Reading news via retrofit
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(final Call<NewsResponse> call, final Response<NewsResponse> response) {
                Logger.d(TAG, "Got response success = " + response.isSuccessful());
                getPresenter().clearResults();
                readResponse(response);
                getPresenter().onStopReading();
            }

            @Override
            public void onFailure(final Call<NewsResponse> call, final Throwable t) {
                getPresenter().onStopReading();
            }
        });
    }

    @Override
    public void sortReading(final SortBy sortBy) {
        getLastRequest().setSortBy(sortBy);
        startReading();
    }

    @Override
    public void filterReading(final NewsRequest request) {
        setLastRequest(request);
        startReading();
    }

    private void readResponse(final Response<NewsResponse> response) {
        if (response != null && response.isSuccessful() && response.body() != null) {
            getPresenter().onReadMoreNews(response.body().getArticles());
        } else {
            getPresenter().onReadMoreNews(null);
        }
    }

}
