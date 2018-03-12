package com.martynov.newsapp.ui.dialogs;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;

/**
 * Created by mihai on 3/10/2018.
 */

public abstract class BaseDialog extends MvpAppCompatDialogFragment {

    public void show(final FragmentManager fragmentManager) {
        super.show(fragmentManager, getClass().getSimpleName());
    }

}
