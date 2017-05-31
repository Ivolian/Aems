package com.unicorn.aems.menu;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

public interface MenuService {

    @GET("api/v1/appService/menu/user/{userId}")
    Observable<ArrayList<Menu>> getMenu(@Header("Cookie") String cookie, @Path("userId") String userId);

}