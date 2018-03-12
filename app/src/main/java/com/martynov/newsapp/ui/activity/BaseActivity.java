package com.martynov.newsapp.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.martynov.newsapp.R;

import butterknife.ButterKnife;

/**
 * Created by mihai on 3/8/2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onViewCreated();
        setSupportActionBar(getToolbar());
    }

    protected abstract Toolbar getToolbar();

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onViewCreated();

}
