package com.dmitryk.reddit.rest.request;


import com.dmitryk.reddit.rest.service.RedditService;
import com.dmitryk.reddit.rest.RedditClient;

import java.util.HashMap;

import rx.Observable;

public abstract class BaseRequestAction {

    protected RedditService api;

    public BaseRequestAction() {
        api = RedditClient.getInstance().getApi();
    }

    public Observable observable;

    public HashMap<String, Object> params;


    public abstract void execute();
}
