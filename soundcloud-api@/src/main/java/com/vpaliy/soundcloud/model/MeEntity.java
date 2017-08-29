package com.vpaliy.soundcloud.model;

import com.google.gson.annotations.SerializedName;

public class MeEntity extends UserEntity {

    public String plan;
    public long private_tracks_count;
    public long private_playlists_count;
    @SerializedName("primary_email_confirmed")
    public boolean isEmailConfirmed;

    public MeEntity(){}
}
