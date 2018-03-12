package com.martynov.newsapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.martynov.newsapp.models.Article;
import com.martynov.newsapp.models.Source;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 3/11/2018.
 */

public class DatabaseManager {

    private static final String SELECT_ARTICLE = "SELECT * FROM " + DatabaseArticleColumns.TABLE_NAME
            + " WHERE " + DatabaseArticleColumns.TITLE + " =?";

    private static DatabaseManager dbManager = new DatabaseManager();

    private DBHelper mDBHelper;

    private DatabaseManager() { }

    public static DatabaseManager getInstance() {
        return dbManager;
    }

    public void init(final Context context) {
        mDBHelper = new DBHelper(context);
    }

    public void addArticle(final Article article) {
        if (article == null || contains(article)) {
            return;
        }
        final ContentValues cv = new ContentValues();
        cv.put(DatabaseArticleColumns.TITLE, article.getTitle());
        cv.put(DatabaseArticleColumns.DESCRIPTION, article.getDescription());
        cv.put(DatabaseArticleColumns.AUTHOR, article.getAuthor());
        cv.put(DatabaseArticleColumns.URL, article.getUrl());
        cv.put(DatabaseArticleColumns.IMAGE_URL, article.getImageUrl());
        cv.put(DatabaseArticleColumns.PUBLISHED, article.getPublishedAt());
        cv.put(DatabaseArticleColumns.SOURCE, article.getSource().getId());
        final SQLiteDatabase database = mDBHelper.getWritableDatabase();
        database.insert(DatabaseArticleColumns.TABLE_NAME, null, cv);
        database.close();
    }
    public void removeArticle(final Article article) {
        if (article == null) {
            return;
        }
        final SQLiteDatabase database = mDBHelper.getWritableDatabase();
        database.delete(DatabaseArticleColumns.TABLE_NAME, DatabaseArticleColumns.ID + "=?", new String[] { "" + article.getId()});
        database.close();
    }

    public void addSource(final Source source) {
        if (source == null) {
            return;
        }
        final ContentValues cv = new ContentValues();
        cv.put(DatabaseSourceColumns.ID, source.getId());
        cv.put(DatabaseSourceColumns.NAME, source.getName());
        cv.put(DatabaseSourceColumns.DESCRIPTION, source.getDescription());
        cv.put(DatabaseSourceColumns.CATEGORY, source.getCategory());
        cv.put(DatabaseSourceColumns.COUNTRY, source.getCountry());
        cv.put(DatabaseSourceColumns.LANGUAGE, source.getLanguage().toString());
        cv.put(DatabaseSourceColumns.URL, source.getUrl());
    }

    public boolean contains(final Article article) {
        if (article != null) {
            final SQLiteDatabase database = mDBHelper.getReadableDatabase();
            final Cursor cursor = database.rawQuery(SELECT_ARTICLE, new String[]{ article.getTitle() });
            final boolean contains = cursor.getCount() > 0;
            cursor.close();
            database.close();
            return contains;
        }
        return false;
    }

    public List<Article> readArticles() {
        final List<Article> articles = new ArrayList<>();
        final SQLiteDatabase database = mDBHelper.getWritableDatabase();
        final Cursor cursor = database.rawQuery(DBHelper.GET_ALL_DATA, null);
        if (cursor.moveToFirst()) {
            int idIdx = cursor.getColumnIndex(DatabaseArticleColumns.ID);
            int titleIdx = cursor.getColumnIndex(DatabaseArticleColumns.TITLE);
            int descriptionIdx = cursor.getColumnIndex(DatabaseArticleColumns.DESCRIPTION);
            int authorIdx = cursor.getColumnIndex(DatabaseArticleColumns.AUTHOR);
            int urlIdx = cursor.getColumnIndex(DatabaseArticleColumns.URL);
            int imageIdx = cursor.getColumnIndex(DatabaseArticleColumns.IMAGE_URL);
            int publishedIdx = cursor.getColumnIndex(DatabaseArticleColumns.PUBLISHED);
            int sourceIdx = cursor.getColumnIndex(DatabaseArticleColumns.SOURCE);
            do {
                final Article article = new Article();
                article.setId(cursor.getInt(idIdx));
                article.setTitle(cursor.getString(titleIdx));
                article.setDescription(cursor.getString(descriptionIdx));
                article.setAuthor(cursor.getString(authorIdx));
                article.setUrl(cursor.getString(urlIdx));
                article.setImageUrl(cursor.getString(imageIdx));
                article.setPublishedAt(cursor.getString(publishedIdx));
                article.setFavourite(true);
//                article.setSource();
                articles.add(article);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return articles;
    }

}
