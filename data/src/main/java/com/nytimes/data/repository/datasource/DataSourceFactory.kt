package com.nytimes.data.repository.datasource

import io.reactivex.Observable

abstract class DataSourceFactory<T> {
    abstract fun create(): Observable<DataSource<T>>
}