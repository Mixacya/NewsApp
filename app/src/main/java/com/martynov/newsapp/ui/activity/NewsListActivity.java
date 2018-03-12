package com.martynov.newsapp.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.martynov.newsapp.R;
import com.martynov.newsapp.adapter.NewsAdapter;
import com.martynov.newsapp.models.Article;
import com.martynov.newsapp.ui.dialogs.FilterDialog;
import com.martynov.newsapp.ui.dialogs.SortDialog;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsListActivity extends BaseListActivity {

    @BindView(R.id.btn_sort)
    Button mSortBtn;
    @BindView(R.id.btn_filter)
    Button mFilterBtn;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_list;
    }

    @Override
    public void setSortingOn(final boolean isSortingOn) {
        final int textColor = getResources().getColor(isSortingOn ? R.color.colorAccent : android.R.color.black);
        mSortBtn.setTextColor(textColor);
    }

    @Override
    public void setFilteringOn(final boolean isFilteringOn) {
        final int textColor = getResources().getColor(isFilteringOn ? R.color.colorAccent : android.R.color.black);
        mFilterBtn.setTextColor(textColor);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.activity_news_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_favourite:
                startActivity(new Intent(this, FavoriteListActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_sort)
    public void onSortClick() {
        SortDialog.newInstance(mPresenter).show(getSupportFragmentManager(), "Sort");
    }

    @OnClick(R.id.btn_filter)
    public void onFilterClick() {
        FilterDialog.newInstance(mPresenter.getLastRequest(), mPresenter.getFilterDialogListener()).show(getSupportFragmentManager());
    }


}
