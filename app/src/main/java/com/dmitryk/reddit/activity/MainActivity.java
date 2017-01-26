package com.dmitryk.reddit.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmitryk.reddit.adapter.PostRecyclerAdapter;
import com.dmitryk.reddit.bus.Bus;
import com.dmitryk.reddit.bus.event.OnError;
import com.dmitryk.reddit.bus.event.OnMorePostsReceived;
import com.dmitryk.reddit.bus.event.OnPostsReceived;
import com.dmitryk.reddit.rest.model.Post;
import com.dmitryk.reddit.rest.request.GetPosts;
import com.dmitryk.reddit.ui.EndlessRecyclerViewScrollListener;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import reddit.dmitryk.com.reddit.R;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_LIST = "state_list";
    private static final String STATE_DATA = "state_data";

    @BindView(R.id.recycler_view) android.support.v7.widget.RecyclerView recyclerView;
    @BindView(R.id.swipe) SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private EndlessRecyclerViewScrollListener endlessScrollListener;

    private List<Post> posts = new ArrayList<>();

    private Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayUseLogoEnabled(true);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_reddit);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFirstPage();
            }
        });


        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostRecyclerAdapter(posts, this);
        recyclerView.setAdapter(adapter);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                requestPosts();
            }
        };
        recyclerView.setOnScrollListener(endlessScrollListener);

    }

    /**
     * Request posts based of current page
     */
    private void requestPosts() {
        if (posts.size() > 1) {
            Post last = posts.get(posts.size() - 2);
            requestNextPage(last.getName());
        } else {
            requestFirstPage();
        }
    }

    /**
     * Request next page
     */
    private void requestNextPage(String after) {
        new GetPosts(after).execute();
    }

    /**
     * Request first page
     */
    private void requestFirstPage() {
        new GetPosts(null).execute();
    }


    /**
     * Triggered with bus when first 10 posts received
     */
    @Subscribe
    public void onPostsReceived(OnPostsReceived event) {
        posts.clear();
        posts.addAll(event.getPosts());
        posts.add(Post.empty());
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Triggered with bus when next page received
     */
    @Subscribe
    public void onMorePostsReceived(OnMorePostsReceived event) {
        posts.remove(posts.size() - 1); // remove empty post
        posts.addAll(event.getPosts());
        posts.add(Post.empty());
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Triggered with bus on network or http error
     */
    @Subscribe
    public void onError(OnError event) {

        swipeRefreshLayout.setRefreshing(false);
        endlessScrollListener.resetState();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bus.getDefault().register(this);
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        } else {
            requestPosts();
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(STATE_LIST, listState);
        outState.putSerializable(STATE_DATA, posts.toArray());
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        if (state != null) {
            listState = state.getParcelable(STATE_LIST);
            List objects = Arrays.asList((Object[]) state.getSerializable(STATE_DATA));
            posts.clear();
            posts.addAll((List<Post>) objects);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bus.getDefault().unregister(this);
    }
}
