package com.martynov.newsapp.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.martynov.newsapp.models.Article;

import java.util.List;

/**
 * Created by mihai on 3/9/2018.
 */

public interface NewsListView extends MvpView {

    void addNews(final List<Article> articles);

    void clearNews();

    void showProgress(final boolean show);

    void showMessage(final String message);

    void setSortingOn(final boolean isSortingOn);

    void setFilteringOn(final boolean isFilteringOn);

}
