package com.unicorn.aems.airport.service;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.respository.AirportRepository;
import com.unicorn.aems.app.dagger.App;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@App
public class AirportService {

    private final AirportRepository airportRepository;

    @Inject
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Observable<List<Airport>> list() {
        return airportRepository.list();
    }

    public Observable<List<Airport>> listByNameOrPinyin(String query) {
        return airportRepository.listByNameOrPinyin(query);
    }

    public Observable<Airport> getDefaultAirport() {
        return airportRepository.firstOrderByName();
    }

    public Observable<List<Airport>> initAirportData() {
        return airportRepository.insertOrReplace(
                Arrays.asList(
                        new Airport("1", "北京机场", "PEK", "http://192.168.7.207:8000/aems/"),
                        new Airport("2", "长春机场", "CGQ", "http://172.31.129.140:8080/aems/"),
                        new Airport("3", "测试机场", "TEST", "http://172.16.29.203:8008/aems/")
                )
        );
    }

}
