package com.unicorn.aems.airport.model;

import me.yokeyword.indexablerv.IndexableEntity;

public class Airport implements IndexableEntity {

    private String name;

    public Airport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

}
