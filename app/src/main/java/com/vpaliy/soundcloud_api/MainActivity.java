package com.vpaliy.soundcloud_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vpaliy.soundcloud.auth.SoundCloudAuth;
import com.vpaliy.soundcloud.model.Token;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Token token=new Token();
        SoundCloudAuth.create(Config.CLIENT_ID,Config.CLIENT_SECRET_ID)
                .tokenWithAuthCode(token.refresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Token>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Token token) {
                        if(token!=null){
                            Log.d(TAG,token.toString());
                        }else{
                            Log.d(TAG,"Null");
                        }
                    }
                });
    }

}
