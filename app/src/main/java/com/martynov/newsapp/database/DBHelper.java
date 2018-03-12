package com.martynov.newsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mihai on 3/11/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String GET_ALL_DATA = "SELECT * FROM " + DatabaseArticleColumns.TABLE_NAME;

    private static final String DB_NAME = "NewsFavourDatabase";
    private static final int DB_VERSION = 1;

    private static final String TYPE_INT = " INTEGER";
    private static final String TYPE_TEXT = " TEXT";

    private static final String SQL_CREATE = "CREATE TABLE " + DatabaseArticleColumns.TABLE_NAME + " ( "
            + DatabaseArticleColumns.ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT, "
            + DatabaseArticleColumns.TITLE + TYPE_TEXT + ", "
            + DatabaseArticleColumns.DESCRIPTION + TYPE_TEXT +", "
            + DatabaseArticleColumns.PUBLISHED + TYPE_TEXT + ", "
            + DatabaseArticleColumns.URL + TYPE_TEXT + ", "
            + DatabaseArticleColumns.IMAGE_URL + TYPE_TEXT + ", "
            + DatabaseArticleColumns.AUTHOR + TYPE_TEXT + ", "
            + DatabaseArticleColumns.SOURCE + TYPE_INT + ");";

    private static final String SQL_TABLE_SOURCE= "CREATE TABLE " + DatabaseSourceColumns.TABLE_NAME + " ( "
            + DatabaseSourceColumns.ID + TYPE_TEXT + ", "
            + DatabaseSourceColumns.NAME + TYPE_TEXT + ", "
            + DatabaseSourceColumns.DESCRIPTION + TYPE_TEXT + ", "
            + DatabaseSourceColumns.CATEGORY + TYPE_TEXT + ", "
            + DatabaseSourceColumns.COUNTRY + TYPE_TEXT + ", "
            + DatabaseSourceColumns.LANGUAGE + TYPE_TEXT + ", "
            + DatabaseSourceColumns.URL + TYPE_TEXT + ");";

    public DBHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
        sqLiteDatabase.execSQL(SQL_TABLE_SOURCE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int i, final int i1) {

    }
}
