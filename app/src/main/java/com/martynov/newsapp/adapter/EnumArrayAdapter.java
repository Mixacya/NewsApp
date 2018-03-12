package com.martynov.newsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.martynov.newsapp.models.Category;
import com.martynov.newsapp.models.Country;
import com.martynov.newsapp.models.Language;

import java.util.List;

/**
 * Created by mihai on 3/11/2018.
 */

public class EnumArrayAdapter<T> extends ArrayAdapter<T> {

    public EnumArrayAdapter(@NonNull final Context context, @NonNull final T[] objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    public EnumArrayAdapter(@NonNull final Context context, @NonNull final List<T> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        final TextView textView = listItem.findViewById(android.R.id.text1);
        final T object = getItem(position);
        if (textView != null && object != null) {
            if (object instanceof Category) {
                textView.setText(((Category) object).getLabelRes());
            } else {
                textView.setText(object.toString());
            }
        }

        return listItem;
    }

    @Override
    public View getDropDownView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
