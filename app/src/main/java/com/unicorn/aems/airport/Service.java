package com.unicorn.aems.airport;

import java.util.List;

import rx.Observable;

public interface Service<T> {

    Observable<List<T>> getAll();

}
