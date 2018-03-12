package com.martynov.newsapp.mvp.model;

import android.support.annotation.NonNull;

import com.martynov.newsapp.models.NewsRequest;
import com.martynov.newsapp.models.SortBy;
import com.martynov.newsapp.mvp.presenter.NewsPresenter;

/**
 * Created by mihai on 3/9/2018.
 */

public abstract class BaseNewsModel {

    private NewsPresenter mPresenter;
    private NewsRequest mLastRequest;

    public BaseNewsModel(@NonNull final NewsPresenter presenter) {
        mPresenter = presenter != null ? presenter : new NewsPresenter();
    }

    public abstract void startReading();

    public abstract void sortReading(final SortBy sortBy);

    public abstract void filterReading(final NewsRequest request);

    public NewsRequest getLastRequest() {
        if (mLastRequest == null) {
            mLastRequest = new NewsRequest();
        }
        return mLastRequest;
    }

    public void setLastRequest(final NewsRequest lastRequest) {
        mLastRequest = lastRequest;
    }

    protected NewsPresenter getPresenter() {
        return mPresenter;
    }
}
