package com.unicorn.aems.login;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LoginInfo {

    private String airport;

    @Id
    private String account;

    private String pwd;

    @Generated(hash = 80000661)
    public LoginInfo(String airport, String account, String pwd) {
        this.airport = airport;
        this.account = account;
        this.pwd = pwd;
    }

    @Generated(hash = 1911824992)
    public LoginInfo() {
    }

    public String getAirport() {
        return this.airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
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


}
