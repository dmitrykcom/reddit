package com.dmitryk.reddit.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmitryk.reddit.rest.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import reddit.dmitryk.com.reddit.R;

public abstract class BaseViewHolder extends ViewHolder<Post> {

    @BindView(R.id.score) TextView score;
    @BindView(R.id.comments_count) TextView comments;
    @BindView(R.id.info) TextView info;


    public BaseViewHolder(View itemView, Context context) {
        super(itemView, context);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void populateHeader(Post model) {

        /* You could use parametrized string in strings.xml,
         but it's easier to handle separators this way */
        String sub = model.getSubreddit() + " • ";
        String source = model.getDomain();
        String header = sub + model.getPrettyDate();
        if (!TextUtils.isEmpty(source)) {
            header += " • " + source;
        }
        info.setText(header);
    }

    @Override
    public void populateFooter(Post model) {
        score.setText(model.getScore());
        comments.setText(model.getCommentsCount());
    }

    @Override
    public void populateContent(final Post model) {
        itemView.findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(model.getUrl()));
                context.startActivity(i);
            }
        });
    }
}
