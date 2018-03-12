package com.martynov.newsapp.ui.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;

import com.martynov.newsapp.R;
import com.martynov.newsapp.utils.DateHelper;
import com.martynov.newsapp.utils.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mihai on 3/11/2018.
 */

public class CalendarDialog extends BaseDialog implements CalendarView.OnDateChangeListener {

    private static final String TAG = "CalendarDialog";

    private OnChooseDateListener mChooseDateListener;

    public interface OnChooseDateListener {
        void onChooseDate(final Date date);
    }

    public static CalendarDialog newInstance(final OnChooseDateListener chooseDateListener) {
        CalendarDialog calendarView = new CalendarDialog();
        calendarView.mChooseDateListener = chooseDateListener;
        return calendarView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final CalendarView calendarView = new CalendarView(getActivity());
        calendarView.setOnDateChangeListener(this);
        return new AlertDialog.Builder(getActivity()).setTitle("Choose date").setView(calendarView).create();
    }

    @Override
    public void onSelectedDayChange(@NonNull final CalendarView calendarView, final int year, final int month, final int day) {
        final Calendar calendar = new GregorianCalendar(year, month, day);
        Logger.d(TAG, "Choosed date " + DateHelper.dateToString(calendar.getTime()));
        if (mChooseDateListener != null) {
            mChooseDateListener.onChooseDate(calendar.getTime());
        }
        dismiss();
    }

    public OnChooseDateListener getChooseDateListener() {
        return mChooseDateListener;
    }

    public void setChooseDateListener(final OnChooseDateListener chooseDateListener) {
        mChooseDateListener = chooseDateListener;
    }

}
