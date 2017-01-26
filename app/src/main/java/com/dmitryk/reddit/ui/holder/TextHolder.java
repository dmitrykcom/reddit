package com.dmitryk.reddit.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmitryk.reddit.rest.model.Post;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import reddit.dmitryk.com.reddit.R;

class TextHolder extends BaseViewHolder {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.thumbnail) ImageView thumbnail;


    public TextHolder(View itemView, Context context) {
        super(itemView, context);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void populateContent(Post model) {
        super.populateContent(model);

        title.setText(model.getTitle());
        if (model.getDefaultThumbnail() != null) {
            thumbnail.setVisibility(View.VISIBLE);
            Picasso.with(context).load(model.getDefaultThumbnail().getUrl()).into(thumbnail);
        } else {
            thumbnail.setVisibility(View.GONE);
        }
    }

}
