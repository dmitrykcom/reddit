package com.dmitryk.reddit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dmitryk.reddit.ui.holder.ViewHolder;
import com.dmitryk.reddit.ui.holder.ViewHolderFactory;
import com.dmitryk.reddit.rest.model.Post;

import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<ViewHolder<Post>> {

    private List<Post> items;
    private Context context;


    public PostRecyclerAdapter(List<Post> posts, Context context) {
        this.items = posts;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        Post post = items.get(position);
        return post.getType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderFactory.createViewHolder(parent, viewType, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder<Post> holder, int position) {
        holder.populateView(get(position));
    }

    private Post get(int position) {
        return items.get(position);
    }

}
