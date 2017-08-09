package com.vpaliy.soundcloud.auth;

import android.text.TextUtils;

import com.vpaliy.soundcloud.SoundCloud;
import com.vpaliy.soundcloud.model.Token;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

@SuppressWarnings({"unused"})
public class SoundCloudAuth {

    private String clientId;
    private String clientSecret;
    private String redirectUri;

    private String GRANT_TYPE       = "grant_type";
    private String CLIENT_ID        = "client_id";
    private String CLIENT_SECRET    = "client_secret";
    private String USERNAME         = "username";
    private String REDIRECT_URI     = "redirect_uri";
    private String CODE             = "code";
    private String RESPONSE_TYPE    = "response_type";
    private String SCOPE            = "scope";
    private String DISPLAY          = "display";
    private String STATE            = "state";
    private String PASSWORD         = "password";


    private SoundCloudAuth(String clientId, String clientSecret){
        if(clientId==null||clientSecret==null){
            throw new IllegalArgumentException("client_id or client_secret is null");
        }
        this.clientId=clientId;
        this.clientSecret=clientSecret;
    }

    @SuppressWarnings("WeakerAccess")
    public class GrantType {
        public static final String AUTH_CODE = "authorization_code";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String PASSWORD = "password";
        public static final String CLIENT_CREDENTIALS = "client_credentials";
        public static final String OAUTH1_TOKEN = "oauth1_token";
    }


    private Interceptor buildInterceptor(){
        return (chain -> {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();
            HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                    .setQueryParameter(CLIENT_ID, clientId)
                    .build();
            Request newRequest = originalRequest.newBuilder()
                    .url(newHttpUrl).build();
            return chain.proceed(newRequest);});
    }

    private Retrofit buildRetrofit(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(buildInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl("https://api.soundcloud.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public SoundCloudAuth addRedirectUri(String redirectUri){
        this.redirectUri=redirectUri;
        return this;
    }

    private AuthService createService(){
        return buildRetrofit().create(AuthService.class);
    }

    public Observable<Token> tokenWithAuthCode(String code){
        if(TextUtils.isEmpty(code)){
            throw new IllegalArgumentException("auth is empty");
        }
        AuthService service=createService();
        Map<String,Object> fieldMap=new HashMap<>();
        fieldMap.put(CLIENT_ID,clientId);
        fieldMap.put(CLIENT_SECRET,clientSecret);
        fieldMap.put(GRANT_TYPE,GrantType.AUTH_CODE);
        fieldMap.put(CODE,code);
        if(redirectUri!=null){
            fieldMap.put(REDIRECT_URI,redirectUri);
        }
        return service.requestToken(fieldMap);
    }
    public Observable<Token> tokenWithCredentials(String username, String password){
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            throw new IllegalArgumentException("username or password is null");
        }
        AuthService service=createService();
        Map<String,Object> fieldMap=new HashMap<>();
        fieldMap.put(CLIENT_ID,clientId);
        fieldMap.put(CLIENT_SECRET,clientSecret);
        fieldMap.put(GRANT_TYPE,GrantType.PASSWORD);
        fieldMap.put(USERNAME,username);
        fieldMap.put(PASSWORD,password);
        if(redirectUri!=null){
            fieldMap.put(REDIRECT_URI,redirectUri);
        }
        return service.requestToken(fieldMap);
    }

    public Observable<Token> refreshToken(Token token){
        if(token==null||TextUtils.isEmpty(token.refresh)){
            throw new IllegalArgumentException("Wrong token");
        }
        AuthService service=createService();
        Map<String,Object> fieldMap=new HashMap<>();
        fieldMap.put(CLIENT_ID, clientId);
        fieldMap.put(CLIENT_SECRET, clientSecret);
        fieldMap.put(GrantType.REFRESH_TOKEN, token.refresh);
        fieldMap.put(GRANT_TYPE, GrantType.REFRESH_TOKEN);
        if(redirectUri!=null) {
            fieldMap.put(REDIRECT_URI, redirectUri);
        }
        return service.requestToken(fieldMap);
    }

    public static SoundCloudAuth create(String clientId,String clientSecret){
        return new SoundCloudAuth(clientId,clientSecret);
    }
}
