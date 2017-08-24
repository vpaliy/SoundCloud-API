package com.vpaliy.soundcloud;

import com.vpaliy.soundcloud.model.AppEntity;
import com.vpaliy.soundcloud.model.MyActivityEntity;
import com.vpaliy.soundcloud.model.CommentEntity;
import com.vpaliy.soundcloud.model.ConnectionEntity;
import com.vpaliy.soundcloud.model.Page;
import com.vpaliy.soundcloud.model.PlaylistEntity;
import com.vpaliy.soundcloud.model.SecretToken;
import com.vpaliy.soundcloud.model.TrackEntity;
import com.vpaliy.soundcloud.model.UserEntity;
import com.vpaliy.soundcloud.model.WebProfileEntity;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

@SuppressWarnings("unused")
public interface SoundCloudService {

    /** Tracks **/

    @GET(Endpoints.TRACKS)
    Single<List<TrackEntity>> searchTracks(@QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACKS)
    Single<Page<TrackEntity>> searchTracksPage(@QueryMap Map<String,Object> options);

    @GET(Endpoints.TRACK_DETAILS)
    Single<TrackEntity> fetchTrack(@Path("id") String id);

    @GET(Endpoints.TRACK_COMMENTS)
    Single<List<CommentEntity>> fetchTrackComments(@Path("id") String id);

    @GET(Endpoints.TRACK_COMMENT)
    Single<CommentEntity> fetchTrackComment(@Path("id") String id);

    @GET(Endpoints.TRACK_FAVORITERS)
    Single<List<UserEntity>> fetchTrackFavoriters(@Path("id") String id);

    @GET(Endpoints.TRACK_FAVORITER)
    Single<UserEntity> fetchTrackFavoriter(@Path("id") String id, @Path("user-id") String userId);

    @GET(Endpoints.TRACK_SECRET_TOKEN)
    Single<SecretToken> fetchTrackSecretToken(@Path("id") String id);

    /** Playlists **/

    @GET(Endpoints.PLAYLISTS)
    Single<List<PlaylistEntity>> searchPlaylists(@QueryMap Map<String,Object> options);

    @GET(Endpoints.PLAYLISTS)
    Single<Page<PlaylistEntity>> searchPlaylistsPage(@QueryMap Map<String,Object> options);

    @GET(Endpoints.PLAYLIST_DETAILS)
    Single<PlaylistEntity> fetchPlaylist(@Path("id") String id);

    @GET(Endpoints.PLAYLIST_TRACKS)
    Single<List<TrackEntity>> fetchPlaylistTracks(@Path("id") String id);

    @GET(Endpoints.PLAYLIST_SECRET_TOKEN)
    Single<SecretToken> fetchPlaylistSecretToken(@Path("id") String id);

    /** Users**/

    @GET(Endpoints.USERS)
    Single<List<UserEntity>> searchUsers(@QueryMap Map<String,Object> options);

    @GET(Endpoints.USERS)
    Single<Page<UserEntity>> searchUsersPage(@QueryMap Map<String,Object> options);

    @GET(Endpoints.USER_DETAILS)
    Single<UserEntity> fetchUser(@Path("id") String id);

    @GET(Endpoints.USER_TRACKS)
    Single<List<TrackEntity>> fetchUserTracks(@Path("id") String id);

    @GET(Endpoints.USER_PLAYLISTS)
    Single<List<PlaylistEntity>> fetchUserPlaylists(@Path("id") String id);

    @GET(Endpoints.USER_FOLLOWERS)
    Single<Page<UserEntity>> fetchUserFollowers(@Path("id") String id);

    @GET(Endpoints.USER_FOLLOWINGS)
    Single<List<UserEntity>> fetchUserFollowings(@Path("id") String id);

    @GET(Endpoints.USER_FOLLOWER)
    Single<UserEntity> fetchUserFollower(@Path("id") String userId, @Path("follower-id") String followerId);

    @GET(Endpoints.USER_FOLLOWING)
    Single<UserEntity> fetchUserFollowing(@Path("id") String userId, @Path("following-id") String followingId);

    @GET(Endpoints.USER_COMMENTS)
    Single<List<CommentEntity>> fetchUserComments(@Path("id") String id);

    @GET(Endpoints.USER_FAVORITES)
    Single<List<TrackEntity>> fetchUserFavoriteTracks(@Path("id") String id);

    @GET(Endpoints.USER_FAVORITE)
    Single<TrackEntity> fetchUserFavoriteTrack(@Path("id") String id, @Path("favorite-id") String trackId);

    @GET(Endpoints.USER_WEB_PROFILES)
    Single<List<WebProfileEntity>> fetchUserWebProfiles(@Path("id") String id);



    /** ME **/

    @GET(Endpoints.ME)
    Single<UserEntity> me();

    @GET(Endpoints.ME_ACTIVITIES)
    Single<List<ConnectionEntity>> fetchMyConnections();

    @GET(Endpoints.ME_CONNECTION)
    Single<ConnectionEntity> fetchMyConnection(@Path("id") String connectionId);

    @GET(Endpoints.ME_ACTIVITIES)
    Single<Page<MyActivityEntity>> fetchMyActivities();

    @GET(Endpoints.SUGGESTED_USERS)
    Single<List<UserEntity>> fetchSuggestedUsers();

    @PUT(Endpoints.ME_FAVORITE_TRACK)
    Completable loveTrack(@Path("id") String id);

    @DELETE(Endpoints.ME_FAVORITE_TRACK)
    Completable unloveTrack(@Path("id") String id);

    @PUT(Endpoints.ME_FOLLOW)
    Completable followUser(@Path("id") String id);

    @DELETE(Endpoints.ME_FOLLOW)
    Completable unfollowUser(@Path("id") String id);

    @GET(Endpoints.ME_FOLLOW)
    Completable amIFollowing(@Path("id") String id);

    @GET(Endpoints.ME_FAVORITE_TRACK)
    Completable didILike(@Path("id") String id);

    /** APPS **/

    @GET(Endpoints.APPS)
    Single<List<AppEntity>> fetchApps();
}
