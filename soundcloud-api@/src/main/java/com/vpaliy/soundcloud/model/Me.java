package com.vpaliy.soundcloud.model;

import com.google.gson.annotations.SerializedName;

public class Me extends User{

    public String plan;
    public long private_tracks_count;
    public long private_playlists_count;
    @SerializedName("primary_email_confirmed")
    public boolean isEmailConfirmed;
}
