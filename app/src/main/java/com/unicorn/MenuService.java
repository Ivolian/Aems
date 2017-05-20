package com.unicorn;

import com.unicorn.aems.Menu;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

public interface MenuService {
        @GET("api/v1/appService/menu/user/{userId}")
        Observable<List<Menu>> getMenu(@Header("Cookie") String cookie,
                                       @Path("userId") String userId);
    }