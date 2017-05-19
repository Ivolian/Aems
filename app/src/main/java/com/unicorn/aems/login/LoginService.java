package com.unicorn.aems.login;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface LoginService {

    @Headers({
            "Content-Type:application/x-www-form-urlencoded",
            "Client-Type: Android"
    })
    @POST("login")
    Observable<SessionInfo> login(@Query("username") String userName, @Query("password") String password);

}