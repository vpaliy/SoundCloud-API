package com.vpaliy.soundcloud.auth;

import com.vpaliy.soundcloud.Endpoints;
import com.vpaliy.soundcloud.model.Token;
import java.util.Map;
import rx.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;

interface AuthService {
    @FormUrlEncoded
    @POST(Endpoints.TOKEN)
    Observable<Token> requestToken(@FieldMap Map<String,Object> authMap);
}
