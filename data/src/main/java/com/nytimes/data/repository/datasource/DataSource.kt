package com.nytimes.data.repository.datasource

import io.reactivex.Observable

interface DataSource<T> {
    fun list(): Observable<List<T>>
}