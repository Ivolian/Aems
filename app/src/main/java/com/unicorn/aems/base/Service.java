package com.unicorn.aems.base;

import java.util.List;

import rx.Observable;

public interface Service<T> {

    Observable<List<T>> get();

}
