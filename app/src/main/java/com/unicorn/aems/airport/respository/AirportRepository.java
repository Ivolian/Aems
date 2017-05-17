package com.unicorn.aems.airport.respository;

import com.unicorn.aems.airport.entity.Airport;

import java.util.List;

import rx.Observable;

public interface AirportRepository {

    Observable<List<Airport>> getAirports();

    Observable<List<Airport>> getAirports(String keyword);

}
