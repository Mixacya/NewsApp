package com.martynov.newsapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mihai on 3/8/2018.
 */

public class NewsResponse {

    @SerializedName("status")
    private String mStatus;

    @SerializedName("totalResults")
    private int mTotalResults;

    @SerializedName("articles")
    private List<Article> mArticles;

    public String getStatus() {
        return mStatus;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public List<Article> getArticles() {
        return mArticles;
    }

    public void setStatus(final String status) {
        mStatus = status;
    }

    public void setTotalResults(final int totalResults) {
        mTotalResults = totalResults;
    }

    public void setArticles(final List<Article> articles) {
        mArticles = articles;
    }
}
