package com.vpaliy.soundcloud;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vpaliy.soundcloud.model.Token;
import com.vpaliy.soundcloud.utils.Adapter;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundCloud {

    private Token token;
    private final String clientId;
    private final SoundCloudService soundCloudService;

    private SoundCloud(String clientId, SoundCloudService soundCloudService) {
        this.clientId = clientId;
        this.soundCloudService = soundCloudService;
    }

    public SoundCloud appendToken(Token token) {
        this.token = token;
        return this;
    }

    public static class Builder {
        private final String apiKey;
        private final Context context;

        private Token token;
        private Interceptor interceptor;
        private OkHttpClient okHttpClient;

        private static final String API_QUERY = "client_id";
        private static final String OAUTH_TOKEN = "oauth_token";

        private static final long CACHE_SIZE = 10 * 1024 * 1024;
        private static final int CONNECT_TIMEOUT = 60;
        private static final int WRITE_TIMEOUT = 60;
        private static final int READ_TIMEOUT = 60;

        public Builder(Context context, String apiKey) {
            if (apiKey == null || context == null) {
                throw new IllegalArgumentException("ClientId or Context cannot be null");
            }
            this.apiKey = apiKey;
            this.context = context;
        }

        public Builder setInterceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public Builder setOkHttpClient(OkHttpClient client) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder setToken(Token token) {
            this.token = token;
            return this;
        }

        private Interceptor buildOkkHttpInterceptor() {
            return (chain -> {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();
                HttpUrl.Builder builder = originalHttpUrl.newBuilder()
                        .addEncodedQueryParameter(API_QUERY, apiKey);
                if (token != null) {
                    builder.addEncodedQueryParameter(OAUTH_TOKEN, token.access);
                }
                HttpUrl newHttpUrl = builder.build();
                Request newRequest = originalRequest.newBuilder()
                        .url(newHttpUrl).build();
                return chain.proceed(newRequest);
            });
        }

        private OkHttpClient provideOkHttpClient(Context context, Interceptor interceptor) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .cache(new Cache(context.getCacheDir(), CACHE_SIZE));
            return builder.build();
        }


        private Retrofit provideRetrofit(OkHttpClient okHttpClient) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new Adapter())
                    .create();
            return new Retrofit.Builder()
                    .baseUrl(buildBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        private HttpUrl buildBaseUrl() {
            final HttpUrl.Builder builder = new HttpUrl.Builder()
                    .scheme("https")
                    .host("api.soundcloud.com")
                    .addEncodedQueryParameter(API_QUERY, apiKey);
            if (token != null) {
                builder.addEncodedQueryParameter(OAUTH_TOKEN, token.access);
            }
            return builder.build();
        }

        public SoundCloud build() {
            if (okHttpClient == null) {
                if (interceptor == null)
                    interceptor = buildOkkHttpInterceptor();
                okHttpClient = provideOkHttpClient(context, interceptor);
            }
            final SoundCloudService service = provideRetrofit(okHttpClient).create(SoundCloudService.class);
            return new SoundCloud(apiKey, service);
        }
    }
}
