package com.unicorn.aems.airport.respository;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.entity.AirportDao;
import com.unicorn.aems.app.dagger.App;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@App
public class AirportRepository {

    private final AirportDao airportDao;

    @Inject
    public AirportRepository(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    public Observable<List<Airport>> list() {
        return airportDao.queryBuilder().rx().list();
    }

    public Observable<List<Airport>> listByNameOrPinyin(String query) {
        WhereCondition con1 = AirportDao.Properties.Name.like("%" + query + "%");
        WhereCondition con2 = AirportDao.Properties.Pinyin.like("%" + query.toLowerCase() + "%");
        return airportDao.queryBuilder()
                .whereOr(con1, con2)
                .rx()
                .list();
    }

    public Observable<Airport> firstOrderByName() {
        return airportDao.queryBuilder()
                .orderAsc(AirportDao.Properties.Name)
                .limit(1)
                .rx()
                .unique();
    }

    public Observable<List<Airport>> insertOrReplace(List<Airport> list) {
        return airportDao.rx().insertOrReplaceInTx(list)
                .map(airports -> (List<Airport>) airports);
    }

}
