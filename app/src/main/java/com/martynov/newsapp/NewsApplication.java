package com.martynov.newsapp;

import android.app.Application;

import com.martynov.newsapp.database.DatabaseManager;

/**
 * Created by mihai on 3/12/2018.
 */

public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.getInstance().init(this);
    }
}
