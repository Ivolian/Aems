package com.unicorn.aems.base;

import java.util.List;

import rx.Observable;

public interface BaseService<T> {

    Observable<List<T>> get();

}
