package com.martynov.newsapp.mvp.model;

import android.support.annotation.NonNull;

import com.martynov.newsapp.database.DatabaseManager;
import com.martynov.newsapp.models.Article;
import com.martynov.newsapp.models.NewsRequest;
import com.martynov.newsapp.models.SortBy;
import com.martynov.newsapp.mvp.presenter.NewsPresenter;

import java.util.List;

/**
 * Created by mihai on 3/12/2018.
 */

public class FavoritesModel extends BaseNewsModel {

    public FavoritesModel(@NonNull final NewsPresenter presenter) {
        super(presenter);
    }

    @Override
    public void startReading() {
        final List<Article> articleList = DatabaseManager.getInstance().readArticles();
        getPresenter().onReadMoreNews(articleList);
    }

    @Override
    public void sortReading(final SortBy sortBy) { }

    @Override
    public void filterReading(final NewsRequest request) { }

}
