package com.vpaliy.soundcloud;

import com.vpaliy.soundcloud.model.Comment;
import com.vpaliy.soundcloud.model.Page;
import com.vpaliy.soundcloud.model.Playlist;
import com.vpaliy.soundcloud.model.SecretToken;
import com.vpaliy.soundcloud.model.Track;
import com.vpaliy.soundcloud.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface SoundCloudService {

    /** Tracks **/

    @GET(Endpoints.TRACKS)
    Observable<List<Track>> searchTracks(@QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACK_DETAILS)
    Observable<Track> fetchTrack(@Path("id") String id);

    @GET(Endpoints.TRACK_COMMENTS)
    Observable<List<Comment>> fetchTrackComments(@Path("id") String id);

    @GET(Endpoints.TRACK_COMMENT)
    Observable<Comment> fetchTrackComment(@Path("id") String id);

    @GET(Endpoints.TRACK_FAVORITERS)
    Observable<List<User>> fetchTrackFavoriters(@Path("id") String id);

    @GET(Endpoints.TRACK_FAVORITER)
    Observable<User> fetchTrackFavoriter(@Path("id") String id, @Path("user-id") String userId);

    @GET(Endpoints.TRACK_SECRET_TOKEN)
    Observable<SecretToken> fetchTrackSecretToken(@Path("id") String id);

    /** Playlists **/

    @GET(Endpoints.PLAYLISTS)
    Observable<List<Playlist>> searchPlaylists(@QueryMap Map<String,Object> options);

    @GET(Endpoints.PLAYLIST_DETAILS)
    Observable<List<Playlist>> fetchPlaylist(@Path("id") String id);

    @GET(Endpoints.PLAYLIST_TRACKS)
    Observable<List<Track>> fetchPlaylistTracks(@Path("id") String id);

    @GET(Endpoints.PLAYLIST_SECRET_TOKEN)
    Observable<SecretToken> fetchPlaylistSecretToken(@Path("id") String id);

    /** Users**/

    @GET(Endpoints.USERS)
    Observable<List<User>> searchUsers(@QueryMap Map<String,Object> options);

}
