package com.dmitryk.reddit.rest.model;

import android.text.Html;

import com.dmitryk.reddit.format.RedditTime;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post implements java.io.Serializable {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_MEDIA = 1;
    public static final int TYPE_EMPTY = 2;


    public static String THUMBNAIL_DEFAULT = "default";

    private String title;
    private String subreddit;
    private String domain;
    private String thumbnail;

    private Thumbnail defaultThumbnail;

    private String url;
    private String score;
    @SerializedName("num_comments") String commentsCount;
    private String name;
    private Boolean hidden;
    String prettyDate;
    @SerializedName("created_utc") private Long created;

    public static Post empty() {
        Post post = new Post();
        return post;
    }

    public String getPrettyDate() {
        // calculate once, no calculation on scroll
        if (prettyDate == null) {
            Date created = new Date(this.created * 1000);
            RedditTime prettyTime = new RedditTime(new Date(System.currentTimeMillis()));
            prettyDate = prettyTime.format(created);
        }
        return prettyDate;
    }


    public String getTitle() {
        return title;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getDomain() {
        return domain;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getUrl() {
        return Html.fromHtml(url).toString();
    }

    public String getScore() {
        return score;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public Long getCreated() {
        return created;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public Thumbnail getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return thumbnail == null && title == null;
    }

    public int getType() {
        if (getThumbnail() == null) {
            return TYPE_EMPTY;
        }

        if (getThumbnail().equals(Post.THUMBNAIL_DEFAULT)) {
            return TYPE_TEXT;
        } else {
            return TYPE_MEDIA;
        }
    }
}