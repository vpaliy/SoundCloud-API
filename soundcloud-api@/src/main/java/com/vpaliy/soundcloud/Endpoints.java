package com.vpaliy.soundcloud;

@SuppressWarnings({"UnusedDeclaration"})
public interface Endpoints {
    String TOKEN = "oauth2/token";

    String TRACKS              = "tracks";
    String TRACK_DETAILS       = "tracks/{id}";
    String TRACK_COMMENTS      = "tracks/{id}/comments";
    String TRACK_FAVORITERS    = "tracks/{id}/favoriters";
    String TRACK_PLAYS         = "tracks/{id}/plays";
    String TRACK_PERMISSIONS   = "tracks/{id}/permissions";

    String PLAYLISTS            = "playlists";
    String PLAYLISTS_ALBUMS     = "playlists?playlist_type=album";
    String PLAYLISTS_DEMO       = "playlists?playlist_type=demo";
    String PLAYLISTS_ARCHIVE    = "playlists?playlist_type=archive";
    String PLAYLISTS_SHOWCASE   = "playlists?playlist_type=showcase";
    String PLAYLISTS_COMPILATION = "playlists?playlist_type=compilation";
    String PLAYLISTS_OTHER       = "playlists?playlist_type=other";
    String PLAYLIST_DETAILS     = "playlists/%d";
    String PLAYLIST_TRACKS      = "playlists/%d/tracks";

    String USERS               = "users";
    String USER_DETAILS        = "users/%d";
    String USER_FOLLOWINGS     = "users/%d/followings";
    String USER_FOLLOWERS      = "users/%d/followers";
    String USER_TRACKS         = "users/%d/tracks";
    String USER_FAVORITES      = "users/%d/favorites";
    String USER_PLAYLISTS      = "users/%d/playlists";

    String MY_DETAILS          = "me";
    String MY_CONNECTIONS      = "me/connections";
    String MY_ACTIVITIES       = "me/activities/tracks";
    String MY_EXCLUSIVE_TRACKS = "me/activities/tracks/exclusive";
    String MY_NEWS             = "me/activities/all/own";
    String MY_TRACKS           = "me/tracks";
    String MY_PLAYLISTS        = "me/playlists";
    String MY_FAVORITES        = "me/favorites";
    String MY_FAVORITE         = "me/favorites/%d";
    String MY_FOLLOWERS        = "me/followers";
    String MY_FOLLOWER         = "me/followers/%d";
    String MY_FOLLOWINGS       = "me/followings";
    String MY_FOLLOWING        = "me/followings/%d";
    String MY_CONFIRMATION     = "me/email-confirmations";
    String MY_FRIENDS          = "me/connections/friends";
    String MY_DEVICES          = "me/devices";

    String SUGGESTED_USERS     = "users/suggested";

    String RESOLVE             = "resolve";

    String APPS                = "apps";

    String SEND_PASSWORD       = "passwords/reset-instructions";
    String CONNECT             = "connect";
    String FACEBOOK_CONNECT    = "connect/via/facebook";


}