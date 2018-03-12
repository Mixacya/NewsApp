package com.martynov.newsapp.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.martynov.newsapp.R;
import com.martynov.newsapp.models.Article;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mihai on 3/12/2018.
 */

public class NewsActivity extends BaseActivity {

    public static final String ARTICLE_KEY = "articleKey";

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    @Override
    protected Toolbar getToolbar() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected void onViewCreated() {
        final Article article = getIntent().getParcelableExtra(ARTICLE_KEY);
        Picasso.get().load(article.getImageUrl()).into(ivIcon);

        tvTitle.setText(article.getTitle());
        tvDescription.setText(article.getDescription());
    }
}
