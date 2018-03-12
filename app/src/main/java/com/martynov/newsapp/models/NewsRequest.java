package com.martynov.newsapp.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.martynov.newsapp.utils.DateHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mihai on 3/9/2018.
 */

public class NewsRequest {

    public static final int DEFAULT_PAGE_SIZE = 20;

    @SerializedName("sources")
    private List<Source> mSources = new ArrayList<>();
    @SerializedName("q")
    private String mQuery;
    @SerializedName("from")
    private Date mFrom;
    @SerializedName("to")
    private Date mTo;
    @SerializedName("language")
    private Language mLanguage;
    @SerializedName("sortBy")
    private SortBy mSortBy;
    @SerializedName("pageSize")
    private int mPageSize;
    @SerializedName("page")
    private int mPage;

    private Category mCategory;
    private Country mCountry;

    public List<Source> getSources() {
        return mSources;
    }

    public String getQuery() {
        return mQuery;
    }

    public Date getFrom() {
        return mFrom;
    }

    public Date getTo() {
        return mTo;
    }

    public Language getLanguage() {
        return mLanguage;
    }

    public SortBy getSortBy() {
        return mSortBy;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public int getPage() {
        return mPage;
    }

    public NewsRequest setSource(final List<Source> sources) {
        mSources = sources;
        return this;
    }

    public NewsRequest setQuery(final String query) {
        mQuery = query;
        return this;
    }

    public NewsRequest setFrom(final Date from) {
        mFrom = from;
        return this;
    }

    public NewsRequest setTo(final Date to) {
        mTo = to;
        return this;
    }

    public NewsRequest setLanguage(final Language language) {
        mLanguage = language;
        return this;
    }

    public NewsRequest setSortBy(final SortBy sortBy) {
        mSortBy = sortBy;
        return this;
    }

    public NewsRequest setPageSize(final int pageSize) {
        mPageSize = pageSize;
        return this;
    }

    public NewsRequest setPage(final int page) {
        mPage = page;
        return this;
    }

    public boolean hasAtLeastOneRequiredField() {
        return !TextUtils.isEmpty(mQuery) || (mSources != null && mSources.size() > 0);
    }

    public boolean hasAtLeastOneField() {
        return !TextUtils.isEmpty(mQuery) || (mSources != null && mSources.size() > 0)
                || (mCategory != null && mCategory != Category.ALL)
                || (mCountry != null && mCountry != Country.ALL.ALL)
                || (mLanguage != null && mLanguage != Language.ALL)
                || mFrom != null && mTo != null
                || mPageSize > 0;
    }

    public Category getCategory() {
        return mCategory;
    }

    public NewsRequest setCategory(final Category category) {
        mCategory = category;
        return this;
    }

    public Country getCountry() {
        return mCountry;
    }

    public NewsRequest setCountry(final Country country) {
        mCountry = country;
        return this;
    }

    public HashMap<String, String> toMap() {
        String encodedQuery = null;
        final HashMap<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(mQuery)) {
            try {
                encodedQuery = URLEncoder.encode(mQuery, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (!TextUtils.isEmpty(encodedQuery)) {
            map.put("q", encodedQuery);
        }

        if (mLanguage != null) {
            map.put("language", mLanguage.toString());
        }

        final String dateFrom = DateHelper.dateToString(mFrom);
        if (!TextUtils.isEmpty(dateFrom)) {
            map.put("from", dateFrom);
        }

        final String dateTo = DateHelper.dateToString(mTo);
        if (!TextUtils.isEmpty(dateTo)) {
            map.put("to", dateTo);
        }

        if (mSortBy != null) {
            map.put("sortBy", mSortBy.toString());
        }

        final String pageSize = String.valueOf(mPageSize);
        if (mPageSize > 0) {
            map.put("pageSize", pageSize);
        }

        final String page = String.valueOf(mPage);
        if (mPage > 0) {
            map.put("page", page);
        }

        final StringBuilder sourceId = new StringBuilder();
        for (Source s : mSources) {
            if (!TextUtils.isEmpty(s.getId())) {
                sourceId.append(s.getId()).append(',');
            }
        }
        if (sourceId.length() > 0) {
            sourceId.deleteCharAt(sourceId.length() - 1);
        }
        if (sourceId.length() > 0) {
            map.put("sources", sourceId.toString());
        }

        return map;
    }

}
