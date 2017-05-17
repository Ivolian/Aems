package com.unicorn.aems.airport.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import me.yokeyword.indexablerv.IndexableEntity;

@Entity
public class Airport implements IndexableEntity {

    @Id
    private String name;

    @NotNull
    private String pinyin;

    @Generated(hash = 1087722055)
    public Airport(String name, @NotNull String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
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

}
