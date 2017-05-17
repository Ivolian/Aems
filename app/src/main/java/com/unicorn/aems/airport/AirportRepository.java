package com.unicorn.aems.airport;

import com.unicorn.aems.airport.model.Airport;
import com.unicorn.aems.airport.model.AirportDao;
import com.unicorn.aems.app.dagger.App;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.yokeyword.indexablerv.PinyinUtil;
import rx.Observable;
import rx.functions.Action1;

@App
public class AirportRepository {

    private AirportDao airportDao;

    @Inject
    public AirportRepository(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    public Observable<List<Airport>> getAirports() {
        if (isLocalAvailable()) {
            return local();
        } else {
            return network();
        }
    }

    private boolean isLocalAvailable() {
        return airportDao.count() != 0;
    }

    private Observable<List<Airport>> local() {
        return airportDao.queryBuilder().rx().list();
    }

    private Observable<List<Airport>> network() {
        return Observable.just(AIRPORT_NAME_STR)
                .map(airportNameStr -> airportNameStr.split("机场"))
                .map(airportNames -> {
                    List<Airport> airports = new ArrayList<>();
                    for (String airportName : airportNames) {
                        String pinyin = PinyinUtil.getPingYin(airportName);
                        airports.add(new Airport(airportName + "机场", pinyin));
                    }
                    return airports;
                })
                .doOnNext(
                        airports -> airportDao.rx().insertOrReplaceInTx(airports).subscribe(new Action1<Iterable<Airport>>() {
                            @Override
                            public void call(Iterable<Airport> airports) {

                            }
                        }));
    }

    public Observable<List<Airport>> getAirports(String keyword) {
        if (isLocalAvailable()) {
            QueryBuilder queryBuilder = airportDao.queryBuilder();
            WhereCondition condition = queryBuilder.or(AirportDao.Properties.Pinyin.like("%" + keyword + "%"),
                    AirportDao.Properties.Name.like("%" + keyword + "%"));
            return airportDao.queryBuilder()
                    .where(condition)
                    .rx().list();
        } else {
            return getAirports();
        }
    }

    private final String AIRPORT_NAME_STR = "白云国际机场宝安国际机场滨海国际机场白塔国际机场白莲机场白塔埠机场北郊机场奔牛机场保安营机场邦达机场长水国际机场长乐国际机场昌北国际机场曹家堡机场潮汕机场朝阳川机场菜坝机场城固机场地窝堡国际机场大水泊机场东山机场东郊机场德宏芒市机场二里半机场二十里铺机场凤凰国际机场福成机场凤凰机场高崎国际机场贡嘎国际机场嘎洒国际机场关公机场高坪机场冠豸山机场虹桥国际机场黄花国际机场河东机场黄龙机场黄金机场荷花机场海浪机场河市机场黄果树机场和平机场江北国际机场晋江机场金湾机场姜营机场锦州湾机场喀纳斯机场流亭国际机场禄口国际机场龙洞堡国际机场龙嘉国际机场龙湾国际机场栎社国际机场两江国际机场路桥机场涟水机场浪头机场蓝田机场庐山机场零陵机场六盘山机场美兰国际机场米林机场南苑机场南郊机场南洋机场那拉提机场浦东国际机场蓬莱国际机场普陀山机场盘龙机场曲阜机场青山机场首都国际机场双流国际机场苏南硕放国际机场三峡机场萨尔图机场三家子机场沙堤机场山海关机场胜利机场思茅机场赛乌苏国际机场天河国际机场桃仙国际机场太平国际机场屯溪机场驼峰机场天柱山机场天吉泰机场桃花源机场腾鳌机场塔城民航机场武宿国际机场吴圩国际机场文山普者黑机场武陵山机场五里铺机场萧山国际机场咸阳国际机场新郑国际机场新桥国际机场兴东国际机场许家坪机场香格里拉机场西郊机场兴凯湖机场西关机场遥墙国际机场玉龙机场扬州泰州机场伊尔施机场玉树巴塘机场周水子国际机场中川机场正定国际机场芷江机场";

}
