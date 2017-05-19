package com.unicorn.aems.airport.respository;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.entity.AirportDao;
import com.unicorn.aems.app.dagger.App;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@App
public class AirportLocalRepository {

    private final AirportDao airportDao;

    @Inject
    public AirportLocalRepository(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    public Observable<List<Airport>> list() {
        return airportDao.queryBuilder().rx().list();
    }

    public Observable<List<Airport>> listByNameOrPinyin(String keyword) {
        WhereCondition conditionName = AirportDao.Properties.Name.like("%" + keyword + "%");
        WhereCondition conditionPinyin = AirportDao.Properties.Pinyin.like("%" + keyword.toLowerCase() + "%");
        return airportDao.queryBuilder().whereOr(conditionName, conditionPinyin).rx().list();
    }

    public Observable<List<Airport>> insertOrReplace(List<Airport> list) {
        return airportDao.rx().insertOrReplaceInTx(list)
                .map(airports -> (List<Airport>) airports);
    }

}