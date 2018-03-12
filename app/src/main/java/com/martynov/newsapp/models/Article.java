package com.martynov.newsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mihai on 3/8/2018.
 */

public class Article implements Parcelable {

    @SerializedName("source")
    private Source mSource;

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("urlToImage")
    private String mImageUrl;

    @SerializedName("publishedAt")
    private String mPublishedAt;

    private int id;
    private boolean mIsFavourite;

    public Article() { }

    protected Article(Parcel in) {
        mAuthor = in.readString();
        mTitle = in.readString();
        mDescription = in.readString();
        mUrl = in.readString();
        mImageUrl = in.readString();
        mPublishedAt = in.readString();
        id = in.readInt();
        mIsFavourite = in.readByte() != 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Source getSource() {
        return mSource;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setSource(final Source source) {
        mSource = source;
    }

    public void setAuthor(final String author) {
        mAuthor = author;
    }

    public void setTitle(final String title) {
        mTitle = title;
    }

    public void setDescription(final String description) {
        mDescription = description;
    }

    public void setUrl(final String url) {
        mUrl = url;
    }

    public void setImageUrl(final String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void setPublishedAt(final String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public boolean isFavourite() {
        return mIsFavourite;
    }

    public void setFavourite(final boolean favourite) {
        mIsFavourite = favourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(mAuthor);
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mUrl);
        parcel.writeString(mImageUrl);
        parcel.writeString(mPublishedAt);
        parcel.writeInt(id);
        parcel.writeByte((byte) (mIsFavourite ? 1 : 0));
    }
}
