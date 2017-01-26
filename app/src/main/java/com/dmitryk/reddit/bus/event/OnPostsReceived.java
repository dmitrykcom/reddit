package com.dmitryk.reddit.bus.event;

import com.dmitryk.reddit.rest.model.Post;

import java.util.List;

public class OnPostsReceived {

    private List<Post> posts;

    public OnPostsReceived(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
