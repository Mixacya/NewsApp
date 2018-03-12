package com.martynov.newsapp.models;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

import com.martynov.newsapp.R;

/**
 * Created by mihai on 3/9/2018.
 */

public enum SortBy {

    NONE("none", R.string.sort_none),
    RELEVANCY("relevancy", R.string.sort_relevancy),
    POPULARITY("popularity", R.string.sort_popularity),
    PUBLISHED_AT("publishedAt", R.string.sort_publishedAt);

    private final String mSort;
    private final int mLabelRes;

    SortBy(final String sort, @StringRes final int labelRes) {
        this.mSort = sort;
        this.mLabelRes = labelRes;
    }

    @Override
    public String toString() {
        return mSort;
    }
}
