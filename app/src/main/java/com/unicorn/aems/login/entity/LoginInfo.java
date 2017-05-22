package com.unicorn.aems.login.entity;

import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.airport.entity.AirportDao;
import com.unicorn.aems.airport.entity.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

@Entity
public class LoginInfo {

    @Id
    private String account;

    @NotNull
    private String pwd;

    @NotNull
    private String airportId;

    @ToOne(joinProperty = "airportId")
    private Airport airport;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1787517186)
    private transient LoginInfoDao myDao;

    @Generated(hash = 1138562563)
    public LoginInfo(String account, @NotNull String pwd,
            @NotNull String airportId) {
        this.account = account;
        this.pwd = pwd;
        this.airportId = airportId;
    }

    @Generated(hash = 1911824992)
    public LoginInfo() {
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAirportId() {
        return this.airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    @Generated(hash = 1374919436)
    private transient String airport__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 343094652)
    public Airport getAirport() {
        String __key = this.airportId;
        if (airport__resolvedKey == null || airport__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AirportDao targetDao = daoSession.getAirportDao();
            Airport airportNew = targetDao.load(__key);
            synchronized (this) {
                airport = airportNew;
                airport__resolvedKey = __key;
            }
        }
        return airport;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 589815967)
    public void setAirport(@NotNull Airport airport) {
        if (airport == null) {
            throw new DaoException(
                    "To-one property 'airportId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.airport = airport;
            airportId = airport.getId();
            airport__resolvedKey = airportId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 69224774)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLoginInfoDao() : null;
    }
    
}
