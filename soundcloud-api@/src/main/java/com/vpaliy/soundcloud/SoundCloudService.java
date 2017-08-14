package com.vpaliy.soundcloud;

import com.vpaliy.soundcloud.model.App;
import com.vpaliy.soundcloud.model.MyActivity;
import com.vpaliy.soundcloud.model.Comment;
import com.vpaliy.soundcloud.model.Connection;
import com.vpaliy.soundcloud.model.Page;
import com.vpaliy.soundcloud.model.Playlist;
import com.vpaliy.soundcloud.model.SecretToken;
import com.vpaliy.soundcloud.model.Track;
import com.vpaliy.soundcloud.model.User;
import com.vpaliy.soundcloud.model.WebProfile;
import java.util.List;
import java.util.Map;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

@SuppressWarnings("unused")
public interface SoundCloudService {

    /** Tracks **/

    @GET(Endpoints.TRACKS)
    Single<List<Track>> searchTracks(@QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACK_DETAILS)
    Single<Track> fetchTrack(@Path("id") String id);

    @GET(Endpoints.TRACK_COMMENTS)
    Single<List<Comment>> fetchTrackComments(@Path("id") String id);

    @GET(Endpoints.TRACK_COMMENT)
    Single<Comment> fetchTrackComment(@Path("id") String id);

    @GET(Endpoints.TRACK_FAVORITERS)
    Single<List<User>> fetchTrackFavoriters(@Path("id") String id);

    @GET(Endpoints.TRACK_FAVORITER)
    Single<User> fetchTrackFavoriter(@Path("id") String id, @Path("user-id") String userId);

    @GET(Endpoints.TRACK_SECRET_TOKEN)
    Single<SecretToken> fetchTrackSecretToken(@Path("id") String id);

    /** Playlists **/

    @GET(Endpoints.PLAYLISTS)
    Single<List<Playlist>> searchPlaylists(@QueryMap Map<String,Object> options);

    @GET(Endpoints.PLAYLIST_DETAILS)
    Single<List<Playlist>> fetchPlaylist(@Path("id") String id);

    @GET(Endpoints.PLAYLIST_TRACKS)
    Single<List<Track>> fetchPlaylistTracks(@Path("id") String id);

    @GET(Endpoints.PLAYLIST_SECRET_TOKEN)
    Single<SecretToken> fetchPlaylistSecretToken(@Path("id") String id);

    /** Users**/

    @GET(Endpoints.USERS)
    Single<List<User>> searchUsers(@QueryMap Map<String,Object> options);

    @GET(Endpoints.USER_DETAILS)
    Single<User> fetchUser(@Path("id") String id);

    @GET(Endpoints.USER_TRACKS)
    Single<List<Track>> fetchUserTracks(@Path("id") String id);

    @GET(Endpoints.USER_PLAYLISTS)
    Single<List<Playlist>> fetchUserPlaylists(@Path("id") String id);

    @GET(Endpoints.USER_FOLLOWERS)
    Single<List<User>> fetchUserFollowers(@Path("id") String id);

    @GET(Endpoints.USER_FOLLOWINGS)
    Single<List<User>> fetchUserFollowings(@Path("id") String id);

    @GET(Endpoints.USER_FOLLOWER)
    Single<User> fetchUserFollower(@Path("id") String userId, @Path("follower-id") String followerId);

    @GET(Endpoints.USER_FOLLOWING)
    Single<User> fetchUserFollowing(@Path("id") String userId, @Path("following-id") String followingId);

    @GET(Endpoints.USER_COMMENTS)
    Single<List<Comment>> fetchUserComments(@Path("id") String id);

    @GET(Endpoints.USER_FAVORITES)
    Single<List<Track>> fetchUserFavoriteTracks(@Path("id") String id);

    @GET(Endpoints.USER_FAVORITE)
    Single<Track> fetchUserFavoriteTrack(@Path("id") String id,@Path("favorite-id") String trackId);

    @GET(Endpoints.USER_WEB_PROFILES)
    Single<List<WebProfile>> fetchUserWebProfiles(@Path("id") String id);


    /** ME **/

    @GET(Endpoints.ME)
    Single<User> me();

    @GET(Endpoints.ME_ACTIVITIES)
    Single<List<Connection>> fetchMyConnections();

    @GET(Endpoints.ME_CONNECTION)
    Single<Connection> fetchMyConnection(@Path("id") String connectionId);

    @GET(Endpoints.ME_ACTIVITIES)
    Single<Page<MyActivity>> fetchMyActivities();

    @GET(Endpoints.SUGGESTED_USERS)
    Single<List<User>> fetchSuggestedUsers();

    /** APPS **/

    @GET(Endpoints.APPS)
    Single<List<App>> fetchApps();
}
