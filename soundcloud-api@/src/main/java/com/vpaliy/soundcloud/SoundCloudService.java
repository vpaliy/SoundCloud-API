package com.vpaliy.soundcloud;

import com.vpaliy.soundcloud.model.Page;
import com.vpaliy.soundcloud.model.Track;
import com.vpaliy.soundcloud.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface SoundCloudService {
    @GET(Endpoints.ME)
    Observable<User> fetchMe();

    @GET(Endpoints.USERS)
    Observable<List<User>> fetchUsers(@QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACKS)
    Observable<List<Track>> fetchTracks(@QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACK_DETAILS)
    Observable<Track> fetchTrackDetails(@Path("id") String id);

    @GET(Endpoints.TRACK_DETAILS)
    Observable<Track> fetchTrackDetails(@Path("id") String id,
                                        @QueryMap Map<String,Object> options);


}
