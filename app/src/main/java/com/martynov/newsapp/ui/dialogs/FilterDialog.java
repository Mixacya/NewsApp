package com.martynov.newsapp.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.martynov.newsapp.R;
import com.martynov.newsapp.adapter.EnumArrayAdapter;
import com.martynov.newsapp.models.Category;
import com.martynov.newsapp.models.Country;
import com.martynov.newsapp.models.Language;
import com.martynov.newsapp.models.NewsRequest;
import com.martynov.newsapp.models.Source;
import com.martynov.newsapp.models.SourceResponse;
import com.martynov.newsapp.net.NewsApiService;
import com.martynov.newsapp.net.NewsServiceCreator;
import com.martynov.newsapp.utils.DateHelper;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mihai on 3/11/2018.
 */

public class FilterDialog extends BaseDialog {

    public interface Listener {
        void onApply(final NewsRequest newsRequest);
    }

    @BindView(R.id.sp_category)
    Spinner spCategoryView;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.sp_language)
    Spinner spLanguageView;
    @BindView(R.id.sp_country)
    Spinner spCountryView;
    @BindView(R.id.et_date_from)
    EditText tvDateFrom;
    @BindView(R.id.et_date_to)
    EditText tvDateTo;
    @BindView(R.id.pb_filter_dialog)
    ProgressBar mProgressBar;

    private final List<Source> mSourceList = new ArrayList<>();
    private final List<Source> mSelectedSourceList = new ArrayList<>();

    private Date mDateFrom;
    private Date mDateTo;

    private Listener mListener;
    private NewsApiService mNewsApiService;
    private NewsRequest mLastRequest;

    public static FilterDialog newInstance(@Nullable final NewsRequest lastRequest, @Nullable final Listener listener) {
        final FilterDialog dialog = new FilterDialog();
        dialog.mListener = listener;
        dialog.mLastRequest = lastRequest;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_filter, null);
        ButterKnife.bind(this, view);

        mNewsApiService = NewsServiceCreator.getInstance().createService(NewsApiService.class);

        final EnumArrayAdapter<Category> categoryAdapter = new EnumArrayAdapter<>(getActivity(), Category.values());
        final ArrayAdapter<Language> languageAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Language.values());
        final ArrayAdapter<Country> countryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Country.values());

        spCategoryView.setAdapter(categoryAdapter);
        spLanguageView.setAdapter(languageAdapter);
        spCountryView.setAdapter(countryAdapter);

        spCategoryView.setOnItemSelectedListener(mItemSelectedListener);
        spLanguageView.setOnItemSelectedListener(mItemSelectedListener);
        spCountryView.setOnItemSelectedListener(mItemSelectedListener);

        tvSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final SourcesDialog sourcesDialog = SourcesDialog.createInstance(mSourceList, mSelectedSourceList, mSourcesDialogListener);
                sourcesDialog.show(getFragmentManager());
            }
        });

        tvDateFrom.setOnClickListener(mOnClickDateView);
        tvDateTo.setOnClickListener(mOnClickDateView);

        if (mLastRequest != null) {
            if (mLastRequest.getCategory() != null) {
                spCategoryView.setSelection(mLastRequest.getCategory().index());
            }

            if (mLastRequest.getCountry() != null) {
                spCountryView.setSelection(mLastRequest.getCountry().index());
            }

            if (mLastRequest.getLanguage() != null) {
                spLanguageView.setSelection(mLastRequest.getLanguage().index());
            }

            mDateFrom = mLastRequest.getFrom();
            if (mDateFrom != null) {
                tvDateFrom.setText(DateHelper.dateToString(mDateFrom));
            }

            mDateTo = mLastRequest.getTo();
            if (mDateTo != null) {
                tvDateTo.setText(DateHelper.dateToString(mDateTo));
            }

            mSelectedSourceList.addAll(mLastRequest.getSources());
            updateSelectedSourcesText();
        }

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Filter")
                .setPositiveButton(android.R.string.ok, mOnClickPositive)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void updateSelectedSourcesText() {
        if (mSourceList.isEmpty()) {
            tvSource.setText(R.string.no_source_to_choose);
            return;
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for (Source source : mSelectedSourceList) {
            stringBuilder.append(source.getName()).append(", ");
        }
        final int length = stringBuilder.length();
        if (length > 1) {
            stringBuilder.delete(length - 2, length - 1);
            tvSource.setText(stringBuilder.toString());
        } else {
            tvSource.setText(R.string.no_source_selected);
        }
    }

    private final DialogInterface.OnClickListener mOnClickPositive = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(final DialogInterface dialogInterface, final int i) {
            final Category category = (Category) spCategoryView.getSelectedItem();

            Country country = (Country) spCountryView.getSelectedItem();
            if (country == Country.ALL) {
                country = null;
            }

            Language language = (Language) spLanguageView.getSelectedItem();
            if (language == Language.ALL) {
                language = null;
            }

            final NewsRequest request = new NewsRequest()
                    .setSource(mSelectedSourceList)
                    .setLanguage(language)
                    .setFrom(mDateFrom)
                    .setTo(mDateTo)
                    .setCategory(category)
                    .setCountry(country);

            if (mListener != null) {
                mListener.onApply(request);
            }
        }
    };

    private final View.OnClickListener mOnClickDateView = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            final FragmentActivity activity = getActivity();
            final FragmentManager fragmentManager = activity != null ? activity.getSupportFragmentManager() : null;
            switch (view.getId()) {
                case R.id.et_date_from:
                    CalendarDialog.newInstance(new CalendarDialog.OnChooseDateListener() {
                        @Override
                        public void onChooseDate(final Date date) {
                            tvDateFrom.setText(DateHelper.dateToString(date));
                            mDateFrom = date;
                        }
                    }).show(fragmentManager);
                    break;
                case R.id.et_date_to:
                    CalendarDialog.newInstance(new CalendarDialog.OnChooseDateListener() {
                        @Override
                        public void onChooseDate(final Date date) {
                            tvDateTo.setText(DateHelper.dateToString(date));
                            mDateTo = date;
                        }
                    }).show(fragmentManager);
                    break;
            }
        }
    };

    private final AdapterView.OnItemSelectedListener mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
            final Category category = (Category) spCategoryView.getSelectedItem();

            Country country = (Country) spCountryView.getSelectedItem();
            if (country == Country.ALL) {
                country = null;
            }

            Language language = (Language) spLanguageView.getSelectedItem();
            if (language == Language.ALL) {
                language = null;
            }

            mProgressBar.setVisibility(View.VISIBLE);
            final Call<SourceResponse> responseCall = mNewsApiService.sources(NewsServiceCreator.API_KEY, category, language, country);
            responseCall.enqueue(new Callback<SourceResponse>() {
                @Override
                public void onResponse(final Call<SourceResponse> call, final Response<SourceResponse> response) {
                    if (response != null && response.isSuccessful() && response.body() != null) {
                        final List<Source> resultSources = response.body().getSources();
                        mSourceList.clear();
                        mSourceList.addAll(resultSources);

                        // Remove selection if filter does not have it
                        final List<Source> selectedCopy = new ArrayList<>(mSelectedSourceList);
                        for (Source source : selectedCopy) {
                            if (!resultSources.contains(source)) {
                                mSelectedSourceList.remove(source);
                            }
                        }
                        updateSelectedSourcesText();
                    }
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(final Call<SourceResponse> call, final Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public void onNothingSelected(final AdapterView<?> adapterView) {
        }
    };

    private final SourcesDialog.Listener mSourcesDialogListener = new SourcesDialog.Listener() {
        @Override
        public void onApply(final List<Source> chosenSources) {
            mSelectedSourceList.clear();
            mSelectedSourceList.addAll(chosenSources);
            updateSelectedSourcesText();
        }
    };

}
