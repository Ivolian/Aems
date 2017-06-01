package com.unicorn.aems.chart;

import com.unicorn.aems.menu.Menu;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

public interface CustomMenuService {

    @GET("api/v1/appService/appCustomMenu/{objectId}")
    Observable<ArrayList<Menu>> getMenu(@Header("Cookie") String cookie, @Path("objectId") String objectId);

}