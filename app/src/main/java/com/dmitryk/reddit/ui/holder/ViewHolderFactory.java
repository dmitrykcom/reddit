package com.dmitryk.reddit.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmitryk.reddit.rest.model.Post;

import reddit.dmitryk.com.reddit.R;

public class ViewHolderFactory {

    private ViewHolderFactory() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static ViewHolder<Post> createViewHolder(ViewGroup parent, int postType, Context context) {
        View view;
        switch (postType) {
            case Post.TYPE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_text, parent, false);
                return new TextHolder(view, context);
            case Post.TYPE_MEDIA:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_media, parent, false);
                return new MediaHolder(view, context);
            case Post.TYPE_EMPTY:
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_empty, parent, false);
                return new EmptyHolder(view, context);
        }

    }

}