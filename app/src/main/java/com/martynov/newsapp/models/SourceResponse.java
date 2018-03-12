package com.martynov.newsapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mihai on 3/11/2018.
 */

public class SourceResponse {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("sources")
    private List<Source> mSources;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(final String status) {
        mStatus = status;
    }

    public List<Source> getSources() {
        return mSources;
    }

    public void setSources(final List<Source> sources) {
        mSources = sources;
    }

}
