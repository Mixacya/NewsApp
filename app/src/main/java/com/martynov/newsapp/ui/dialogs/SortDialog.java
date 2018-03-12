package com.martynov.newsapp.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.martynov.newsapp.models.SortBy;
import com.martynov.newsapp.mvp.presenter.NewsPresenter;

/**
 * Created by mihai on 3/10/2018.
 */

public class SortDialog extends DialogFragment {

    private static NewsPresenter mPresenter;

    public SortDialog() { }

    public static SortDialog newInstance(final NewsPresenter presenter) {
        SortDialog dialog = new SortDialog();
        mPresenter = presenter;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final ArrayAdapter<SortBy> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);

        for (SortBy sort : SortBy.values()) {
            adapter.add(sort);
        }

        return new AlertDialog.Builder(getActivity()).setTitle("Sorting").setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                switch (adapter.getItem(i)) {
                    case NONE:
                        request(SortBy.NONE);
                        break;
                    case RELEVANCY:
                        request(SortBy.RELEVANCY);
                        break;
                    case POPULARITY:
                        request(SortBy.POPULARITY);
                        break;
                    case PUBLISHED_AT:
                        request(SortBy.PUBLISHED_AT);
                        break;
                }
            }
        }).create();
        //return super.onCreateDialog(savedInstanceState);
    }

    private void request(SortBy sortBy) {
        mPresenter.sortReading(sortBy);
    }

}
