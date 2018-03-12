package com.martynov.newsapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mihai on 3/8/2018.
 */

public class Source {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("category")
    private String mCategory;

    @SerializedName("language")
    private Language mLanguage;

    @SerializedName("country")
    private String mCountry;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getCategory() {
        return mCategory;
    }

    public Language getLanguage() {
        return mLanguage;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setId(final String id) {
        mId = id;
    }

    public void setName(final String name) {
        mName = name;
    }

    public void setDescription(final String description) {
        mDescription = description;
    }

    public void setUrl(final String url) {
        mUrl = url;
    }

    public void setCategory(final String category) {
        mCategory = category;
    }

    public void setLanguage(final Language language) {
        mLanguage = language;
    }

    public void setCountry(final String country) {
        mCountry = country;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Source source = (Source) o;

        if (mId != null ? !mId.equals(source.mId) : source.mId != null) return false;
        return mName != null ? mName.equals(source.mName) : source.mName == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }
}
