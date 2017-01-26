package com.dmitryk.reddit.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;

public abstract class ViewHolder<T> extends RecyclerView.ViewHolder {


    private final static int FADE_DURATION = 300;

    protected Context context;

    public ViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    public void populateView(T model) {
        populateHeader(model);
        populateContent(model);
        populateFooter(model);
    }

    public void animate() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        itemView.startAnimation(anim);
    }

    public abstract void populateFooter(T model);

    public abstract void populateContent(T model);

    public abstract void populateHeader(T model);

}