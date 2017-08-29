package com.vpaliy.soundcloud.model;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an OAuth2 access/refresh token pair.
 */
@SuppressWarnings("WeakerAccess")
public class Token  {

    public static final String SCOPE_DEFAULT      = "*";

    public static final String SCOPE_SIGNUP       = "signup";
    public static final String SCOPE_PLAYCOUNT    = "playcount";
    public static final String SCOPE_NON_EXPIRING = "non-expiring";

    private static final String ACCESS_TOKEN  = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String SCOPE         = "scope";
    private static final String EXPIRES_IN    = "expires_in";

    @SerializedName("access_token")
    public String access;
    public String refresh;
    public String scope;
    public long expiresIn;

    public Token(){}

    public final Map<String, String> customParameters = new HashMap<String, String>();

    /** Invalidates the access token */
    public void invalidate() {
        this.access = null;
    }

    /**
     * @return null or the date of expiration of this token
     */
    public Date getExpiresIn() {
        return expiresIn == 0 ? null : new Date(expiresIn);
    }

    public boolean defaultScoped() {
        return scoped(SCOPE_DEFAULT);
    }

    /** @return has token the signup scope ("signup") */
    public boolean signupScoped() {
        return scoped(SCOPE_SIGNUP);
    }

    public boolean scoped(String scope) {
        if (this.scope != null) {
            for (String s : this.scope.split(" "))
                if (scope.equals(s)) return true;
        }
        return false;
    }

    public boolean valid() {
        return access != null && (scoped(SCOPE_NON_EXPIRING) || refresh != null);
    }

    @Override
    public String toString() {
        return "Token{" +
                "access='" + access + '\'' +
                ", refresh='" + refresh + '\'' +
                ", scope='" + scope + '\'' +
                ", expires=" + getExpiresIn() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (o instanceof Token) {
            Token token = (Token) o;
            if (access != null ? !access.equals(token.access) : token.access != null) return false;
            if (refresh != null ? !refresh.equals(token.refresh) : token.refresh != null) return false;
            if (scope != null ? !scope.equals(token.scope) : token.scope != null) return false;
            return true;
        } else {
            return super.equals(o);
        }
    }

    @Override
    public int hashCode() {
        int result = access != null ? access.hashCode() : 0;
        result = 31 * result + (refresh != null ? refresh.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        return result;
    }
}