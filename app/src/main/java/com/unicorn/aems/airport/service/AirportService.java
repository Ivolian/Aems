package com.unicorn.aems.airport.service;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.respository.AirportLocalRepository;
import com.unicorn.aems.airport.respository.AirportNetworkRepository;
import com.unicorn.aems.app.dagger.App;
import com.unicorn.aems.base.BaseService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@App
public class AirportService implements BaseService<Airport> {

    private final AirportLocalRepository localRepository;

    private final AirportNetworkRepository networkRepository;

    @Inject
    public AirportService(AirportLocalRepository localRepository, AirportNetworkRepository networkRepository) {
        this.localRepository = localRepository;
        this.networkRepository = networkRepository;
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
        return networkRepository.list().flatMap(
                localRepository::insertOrReplace);
    }


}
