package com.vpaliy.soundcloud.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public String id;
    public String permalink;
    public String username;
    public String uri;
    public String permalink_url;
    public String avatar_url;
    public String country;
    public String full_name;
    public String city;
    public String description;

    @SerializedName("discogs-name")
    public String discogs_name;

    @SerializedName("myspace-name")
    public String myspace_name;
    public String website;

    @SerializedName("website-tile")
    public String website_title;

    @SerializedName("online")
    public boolean is_online;
    public String track_count;
    public String playlist_count;
    public String followers_count;
    public String followings_count;
    public String public_favorites_count;
    public String avatar_data;
}
