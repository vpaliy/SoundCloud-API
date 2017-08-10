package com.vpaliy.soundcloud;


import android.content.Context;

import com.vpaliy.soundcloud.model.Token;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundCloud {

    private static final String BASE_URL="https://soundcloud.com/";
    private static final String API_QUERY = "client_id";
    private static final String OAUTH_TOKEN="oauth_token";

    private static final long CACHE_SIZE = 10 * 1024 * 1024;
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int READ_TIMEOUT = 60;

    private String clientId;
    private Token token;

    public SoundCloud(String clientId){
        if(clientId==null){
            throw new IllegalArgumentException("ClientId cannot be null");
        }
        this.clientId = clientId;
    }


    private Interceptor provideOkHttpInterceptor(){
        return (chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl.Builder builder=originalHttpUrl.newBuilder()
                    .addEncodedQueryParameter(API_QUERY,clientId);
            if(token!=null){
                builder.addEncodedQueryParameter(OAUTH_TOKEN,token.access);
            }
            HttpUrl newHttpUrl = builder.build();
            Request newRequest = addHeader(originalRequest.newBuilder()
                    .url(newHttpUrl)).build();

            return chain.proceed(newRequest);});
    }

    private Request.Builder addHeader(Request.Builder builder){
        return builder;
    }

    private OkHttpClient provideOkHttpClient(Context context, Interceptor interceptor) {
        Builder builder = new Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE));

        return builder.build();
    }

    private Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://api.soundcloud.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public SoundCloudService createService(Context context){
        OkHttpClient okHttpClient=provideOkHttpClient(context,provideOkHttpInterceptor());
        Retrofit retrofit=provideRetrofit(okHttpClient);
        return retrofit.create(SoundCloudService.class);
    }

    public SoundCloud appendToken(Token token){
        this.token=token;
        return this;
    }

    public static SoundCloud create(String apiKey){
        return new SoundCloud(apiKey);
    }
}
