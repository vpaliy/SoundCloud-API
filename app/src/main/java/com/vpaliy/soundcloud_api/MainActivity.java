package com.vpaliy.soundcloud_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vpaliy.soundcloud.SoundCloud;
import com.vpaliy.soundcloud.SoundCloudService;
import com.vpaliy.soundcloud.model.Page;
import com.vpaliy.soundcloud.model.Track;
import com.vpaliy.soundcloud.model.User;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoundCloud.create(Config.CLIENT_ID)
                .createService(this)
                .fetchTrack("271990006")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Track>() {
                    @Override
                    public void call(Track track) {
                        if (track != null) {
                            Log.d(TAG, track.title);
                            Log.d(TAG, track.id);
                        } else {
                            Log.d(TAG, "null");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }
}
