package com.dmitryk.reddit.rest.service;


import com.dmitryk.reddit.rest.model.Post;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RedditService {

    @GET("/top/.json")
    Observable<List<Post>> getPosts(@Query("after") String after, @Query("limit") Integer limit, @Query("t") String interval);

}
