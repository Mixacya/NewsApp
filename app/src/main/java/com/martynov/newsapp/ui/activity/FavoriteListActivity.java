package com.martynov.newsapp.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.martynov.newsapp.R;
import com.martynov.newsapp.mvp.view.NewsListView;

import butterknife.BindView;

/**
 * Created by mihai on 3/12/2018.
 */

public class FavoriteListActivity extends BaseListActivity implements NewsListView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favorites_list;
    }

    @Override
    public void setSortingOn(final boolean isSortingOn) { }

    @Override
    public void setFilteringOn(final boolean isFilteringOn) { }

}
