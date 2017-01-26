package com.dmitryk.reddit.ui.holder;

import android.content.Context;
import android.view.View;

import com.dmitryk.reddit.rest.model.Post;

class EmptyHolder extends ViewHolder<Post> {


    public EmptyHolder(View itemView, Context context) {
        super(itemView, context);
    }


    @Override
    public void populateFooter(Post model) {

    }

    @Override
    public void populateContent(Post model) {

    }

    @Override
    public void populateHeader(Post model) {

    }
}
