package com.unicorn.aems.airport.respository;

import com.unicorn.aems.airport.entity.Airport;

import java.util.List;

import rx.Observable;

public interface AirportRepository {

    /**
     * 获取所有机场
     */
    Observable<List<Airport>> getAirports();

    /**
     * 根据关键字获取机场
     */
    Observable<List<Airport>> getAirports(String keyword);

}
