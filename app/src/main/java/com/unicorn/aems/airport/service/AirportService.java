package com.unicorn.aems.airport.service;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.respository.AirportLocalRepository;
import com.unicorn.aems.app.dagger.App;
import com.unicorn.aems.base.BaseService;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@App
public class AirportService implements BaseService<Airport> {

    private final AirportLocalRepository localRepository;

//    private final AirportNetworkRepository networkRepository;




    @Inject
    public AirportService(AirportLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public Observable<List<Airport>> get() {
        return Observable.concat(local(), network())
                .filter(list -> list.size() != 0)
                .first();
    }

    public Observable<List<Airport>> getByNameOrPinyin(String keyword) {
        return localRepository.listByNameOrPinyin(keyword)
                .filter(list -> list.size() != 0);
    }

    private Observable<List<Airport>> local() {
        return localRepository.list();
    }

    public Observable<List<Airport>> network() {
//        return networkRepository.list().flatMap(
//                localRepository::insertOrReplace);
//    }
//
//          airportMap.put("测试机场", "http://192.168.7.71:8008/aems,PEK");
//    //airportMap.put("测试机场", "http://172.16.29.203:8008/aems,PEK");
//        airportMap.put("北京机场", "http://10.40.8.142/aems,PEK");
//        airportMap.put("长春机场", "http://172.31.129.140:8080/aems,CGQ");
        return null;
    }

    //
    public Observable<List<Airport>> initAirports() {
        List<Airport> airports = generateAirports();
        return localRepository.insertOrReplace(airports);
    }

    private List<Airport> generateAirports() {
        return Arrays.asList(
                new Airport("1", "北京机场", "PEK", "http://192.168.7.71:8008/aems/"),
                new Airport("2", "长春机场", "CGQ", "http://172.31.129.140:8080/aems/"),
                new Airport("3", "测试机场", "TEST", "http://172.16.29.203:8008/aems/")
        );
    }

}
