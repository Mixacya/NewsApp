package com.martynov.newsapp.models;

/**
 * Created by mihai on 3/9/2018.
 */

public enum Language {

    ALL("all"),
    AR("ar"), DE("de"), EN("en"), ES("es"), FR("fr"),
    HE("he"), IT("it"), NL("nl"), NO("no"), PT("pt"),
    RU("ru"), SE("se"), UD("ud"), ZH("zh");

    private final String mLanguage;

    Language(final String language) {
        this.mLanguage = language;
    }

    @Override
    public String toString() {
        return mLanguage;
    }

    public int index() {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == this) {
                return i;
            }
        }
        return 0;
    }
}
