package com.martynov.newsapp.utils;

import android.util.Log;

import com.martynov.newsapp.BuildConfig;

/**
 * Created by mihai on 3/9/2018.
 */

public class Logger {

    public static void d(final String tag, final String... args) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, argsToMessage(args));
        }
    }

    public static void e(final String tag, final String... args) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, argsToMessage(args));
        }
    }

    public static void e(final String tag, final Throwable throwable, final String... args) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, argsToMessage(args), throwable);
        }
    }

    private static String argsToMessage(final String... args) {
        if (args != null && args.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(args[0]);
            for (int i = 1; i < args.length; i++) {
                stringBuilder.append(' ').append(args[i]);
            }
            return stringBuilder.toString();
        }
        return "null";
    }

}
