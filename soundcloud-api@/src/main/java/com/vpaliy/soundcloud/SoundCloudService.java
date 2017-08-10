package com.vpaliy.soundcloud;

import com.vpaliy.soundcloud.model.Playlist;
import com.vpaliy.soundcloud.model.Track;
import com.vpaliy.soundcloud.model.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface SoundCloudService {
    @GET(Endpoints.MY_DETAILS)
    Observable<User> fetchMe();
}
