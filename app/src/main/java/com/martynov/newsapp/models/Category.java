package com.martynov.newsapp.models;

import android.support.annotation.StringRes;

import com.martynov.newsapp.R;

/**
 * Created by mihai on 3/11/2018.
 */

public enum Category {

    ALL(null, R.string.all),
    BUSINESS("business", R.string.business),
    ENTERTAINMENT("entertainment", R.string.entertainment),
    GENERAL("general", R.string.general),
    HEALTH("health", R.string.health),
    SCIENCE("science", R.string.science),
    SPORTS("sports", R.string.sports),
    TECHNOLOGY("technology", R.string.technology);

    private final String mCategory;
    private final int mLabelRes;

    Category(final String category, @StringRes final int labelRes) {
        this.mCategory = category;
        this.mLabelRes = labelRes;
    }

    @Override
    public String toString() {
        return mCategory;
    }

    @StringRes
    public int getLabelRes() {
        return mLabelRes;
    }

    public int index() {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == this) {
                return i;
            }
        }
        return 0;
    }

}
