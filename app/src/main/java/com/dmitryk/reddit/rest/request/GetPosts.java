package com.dmitryk.reddit.rest.request;

import com.dmitryk.reddit.bus.Bus;
import com.dmitryk.reddit.bus.event.OnMorePostsReceived;
import com.dmitryk.reddit.bus.event.OnPostsReceived;
import com.dmitryk.reddit.rest.model.Post;
import com.dmitryk.reddit.rx.ErrorAction;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GetPosts extends BaseRequestAction {

    private static final int LIMIT = 10;
    private static final String INTERVAL = "day";

    private String lastPostName;

    public GetPosts(String lastPostName) {
        this.lastPostName = lastPostName;
        observable = api.getPosts(lastPostName, LIMIT, INTERVAL);
    }

    @Override
    public void execute() {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Post>>() {
                    @Override
                    public void call(List<Post> posts) {
                        if (lastPostName == null) {
                            Bus.getDefault().post(new OnPostsReceived(posts));
                        } else {
                            Bus.getDefault().post(new OnMorePostsReceived(posts));
                        }
                    }
                }, new ErrorAction());
    }
}
