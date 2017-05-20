package com.unicorn.aems.airport.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import me.yokeyword.indexablerv.IndexableEntity;
import me.yokeyword.indexablerv.PinyinUtil;


@Entity
public class Airport implements IndexableEntity {

    @Id
    private String id;

    @Unique
    private String name;

    @Unique
    private String code;

    @NotNull
    private String requestUrl;

    @NotNull
    private String pinyin;

    @Generated(hash = 1627687154)
    public Airport(String id, String name, String code, @NotNull String requestUrl,
            @NotNull String pinyin) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.requestUrl = requestUrl;
        this.pinyin = pinyin;
    }

    @Keep
    public Airport(String id, String name, String code, String requestUrl) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.requestUrl = requestUrl;
        this.pinyin = PinyinUtil.getPingYin(name);
    }

    @Generated(hash = 648016182)
    public Airport() {
    }

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.name = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
