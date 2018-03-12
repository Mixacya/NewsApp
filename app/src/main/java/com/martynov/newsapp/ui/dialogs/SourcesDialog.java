package com.martynov.newsapp.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.martynov.newsapp.R;
import com.martynov.newsapp.models.Category;
import com.martynov.newsapp.models.Country;
import com.martynov.newsapp.models.Language;
import com.martynov.newsapp.models.Source;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by mihai on 3/11/2018.
 */

public class SourcesDialog extends BaseDialog {

    public interface Listener {
        void onApply(final List<Source> chosenSources);
    }

    private List<Source> mSources;
    private Listener mListener;
    private boolean[] mSourcesChoose;

    public static SourcesDialog createInstance(@NonNull final List<Source> sources, @NonNull final List<Source> selectedSources, final Listener listener) {
        final SourcesDialog sourcesDialog = new SourcesDialog();
        sourcesDialog.mSources = sources;
        sourcesDialog.mListener = listener;
        sourcesDialog.mSourcesChoose = new boolean[sources.size()];
        for (int i = 0; i < sourcesDialog.mSourcesChoose.length; i++) {
            sourcesDialog.mSourcesChoose[i] = selectedSources.contains(sources.get(i));
        }
        return sourcesDialog;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final CharSequence[] sourceTitles = new CharSequence[mSources.size()];
        for (int i = 0; i < sourceTitles.length; i++) {
            sourceTitles[i] = mSources.get(i).getName();
        }

        return new AlertDialog.Builder(getActivity())
                .setMultiChoiceItems(sourceTitles, mSourcesChoose, mChooseListener)
                .setPositiveButton(android.R.string.ok, mOnClickPositive)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private final DialogInterface.OnMultiChoiceClickListener mChooseListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(final DialogInterface dialogInterface, final int i, final boolean b) {
            mSourcesChoose[i] = b;
        }
    };

    private final DialogInterface.OnClickListener mOnClickPositive = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(final DialogInterface dialogInterface, final int flag) {
            final List<Source> chosenSources = new ArrayList<>();
            for (int i = 0; i < mSourcesChoose.length; i++) {
                if (mSourcesChoose[i]) {
                    chosenSources.add(mSources.get(i));
                }
            }
            if (mListener != null) {
                mListener.onApply(chosenSources);
            }
        }
    };

}
