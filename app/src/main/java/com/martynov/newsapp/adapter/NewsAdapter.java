package com.martynov.newsapp.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.martynov.newsapp.R;
import com.martynov.newsapp.database.DatabaseManager;
import com.martynov.newsapp.models.Article;
import com.martynov.newsapp.utils.Logger;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mihai on 3/9/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<Article> mArticles = new ArrayList<>();
    private OnListItemClickListener mOnListItemClickListener;

    public interface OnListItemClickListener {
        void onItemClick(final Article article);
    }

    public NewsAdapter() { }

    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        holder.setArticle(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void addAll(final Collection<Article> articles) {
        if (articles != null) {
            mArticles.addAll(articles);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        mArticles.clear();
        notifyDataSetChanged();
    }

    public void setOnListItemClickListener(final OnListItemClickListener listener) {
        this.mOnListItemClickListener = listener;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tv_description)
        TextView mDescription;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.btn_share)
        ImageButton btnShare;
        @BindView(R.id.btn_favour)
        ImageButton btnFavour;

        private Article mArticle;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setArticle(final Article article) {
            mArticle = article;
            String title = null;
            String description = null;
            String imageUrl = null;
            boolean isFavourite = false;
            if (article != null) {
                title = article.getTitle();
                description = article.getDescription();
                imageUrl = article.getImageUrl();
                isFavourite = article.isFavourite();

            }
            mTitle.setText(title);
            mDescription.setText(description);
            Picasso.get().load(imageUrl)
                    .resizeDimen(R.dimen.list_image_size, R.dimen.list_image_size)
                    .centerCrop().into(ivIcon);
            btnFavour.setActivated(isFavourite);
        }

        @OnClick(R.id.btn_favour)
        public void onFavourClick() {
            final boolean isFavourite = mArticle != null && !mArticle.isFavourite();
            btnFavour.setSelected(isFavourite);
            if (isFavourite) {
                DatabaseManager.getInstance().addArticle(mArticle);
            } else {
                DatabaseManager.getInstance().removeArticle(mArticle);
                mArticles.remove(mArticle);
                notifyDataSetChanged();
            }
        }

        @OnClick(R.id.btn_share)
        public void onShareClick() {

        }

        @Override
        public void onClick(final View view) {
            if (mOnListItemClickListener != null) {
                mOnListItemClickListener.onItemClick(mArticle);
            }
        }
    }

}
