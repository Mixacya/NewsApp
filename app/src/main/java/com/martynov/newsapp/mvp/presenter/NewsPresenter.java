package com.martynov.newsapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.martynov.newsapp.models.Article;
import com.martynov.newsapp.models.Language;
import com.martynov.newsapp.models.NewsResponse;
import com.martynov.newsapp.models.NewsRequest;
import com.martynov.newsapp.models.SortBy;
import com.martynov.newsapp.models.Source;
import com.martynov.newsapp.mvp.model.BaseNewsModel;
import com.martynov.newsapp.mvp.model.FavoritesModel;
import com.martynov.newsapp.mvp.model.NewsModel;
import com.martynov.newsapp.mvp.view.NewsListView;
import com.martynov.newsapp.ui.activity.FavoriteListActivity;
import com.martynov.newsapp.ui.dialogs.FilterDialog;

import java.util.Date;
import java.util.List;

/**
 * Created by mihai on 3/9/2018.
 */
@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsListView> {

    private BaseNewsModel mModel;

    public NewsPresenter() { }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        NewsListView listView = null;
        for (NewsListView view : getAttachedViews()) {
            listView = view;
            if (listView != null) {
                break;
            }
        }

        if (listView!= null && listView instanceof FavoriteListActivity) {
            mModel = new FavoritesModel(this);
        } else {
            mModel = new NewsModel(this);
        }
        startReading();
    }

    public void onReadMoreNews(final List<Article> articles) {
        if (articles != null) {
            getViewState().addNews(articles);
        } else {
            getViewState().showMessage("No results");
        }
    }

    public void clearResults() {
        getViewState().clearNews();
    }

    public void startReading() {
        mModel.startReading();
    }

    public void sortReading(final SortBy sortBy) {
        mModel.sortReading(sortBy);
        getViewState().setSortingOn(sortBy != SortBy.NONE);
    }

    public FilterDialog.Listener getFilterDialogListener() {
        return mFilterDialogListener;
    }

    public NewsRequest getLastRequest() {
        return mModel.getLastRequest();
    }

    public void onStartReading() {
        getViewState().showProgress(true);
    }

    public void onStopReading() {
        getViewState().showProgress(false);
    }

    private final FilterDialog.Listener mFilterDialogListener = new FilterDialog.Listener() {

        @Override
        public void onApply(final NewsRequest request) {
            mModel.filterReading(request);
            getViewState().setFilteringOn(request.hasAtLeastOneField());
        }
    };

}
