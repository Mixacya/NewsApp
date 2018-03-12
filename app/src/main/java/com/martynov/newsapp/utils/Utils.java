package com.martynov.newsapp.utils;

import com.martynov.newsapp.models.Country;

import java.util.Locale;

/**
 * Created by mihai on 3/11/2018.
 */

public class Utils {

    public static Country getCurrentCountry() {
        try {
            return Country.valueOf(Locale.getDefault().getCountry().toUpperCase());
        } catch (IllegalStateException e) {}
        return null;
    }

}
