package com.martynov.newsapp.utils;

import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mihai on 3/9/2018.
 */

public class DateHelper {

    @Nullable
    public static String dateToString(final Date date) {
        if (date != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        }
        return null;
    }

}
