package com.vpaliy.soundcloud.auth;

import com.vpaliy.soundcloud.Endpoints;
import com.vpaliy.soundcloud.model.Token;
import java.util.Map;
import io.reactivex.Single;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;

interface AuthService {
    @FormUrlEncoded
    @POST(Endpoints.TOKEN)
    Single<Token> requestToken(@FieldMap Map<String,Object> authMap);
}
