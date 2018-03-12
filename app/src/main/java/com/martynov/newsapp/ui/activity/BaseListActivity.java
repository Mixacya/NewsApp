package com.martynov.newsapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.martynov.newsapp.R;
import com.martynov.newsapp.adapter.NewsAdapter;
import com.martynov.newsapp.models.Article;
import com.martynov.newsapp.mvp.presenter.NewsPresenter;
import com.martynov.newsapp.mvp.view.NewsListView;
import com.martynov.newsapp.net.NewsServiceCreator;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mihai on 3/9/2018.
 */

public abstract class BaseListActivity extends BaseActivity implements NewsListView {

    @InjectPresenter()
    NewsPresenter mPresenter;

    @BindView(R.id.rv_news)
    RecyclerView mNewsRecyclerView;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private NewsAdapter mAdapter;

    @Override
    protected void onViewCreated() {
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        NewsServiceCreator.getInstance();
        mAdapter = new NewsAdapter();
        mAdapter.setOnListItemClickListener(mOnListItemClickListener);
        mNewsRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.startReading();
            }
        });
    }

    @Override
    public void showMessage(final String message) {
        Snackbar.make(mNewsRecyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(final boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void addNews(final List<Article> articles) {
        getAdapter().addAll(articles);
    }

    @Override
    public void clearNews() {
        getAdapter().clear();
    }

    protected NewsAdapter getAdapter() {
        return mAdapter;
    }


    private final NewsAdapter.OnListItemClickListener mOnListItemClickListener = new NewsAdapter.OnListItemClickListener() {
        @Override
        public void onItemClick(final Article article) {
            final Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
            intent.putExtra(NewsActivity.ARTICLE_KEY, article);
            startActivity(intent);
        }
    };
}
