package com.nytimes.data.repository.datasource

import com.nytimes.data.cache.Cache
import io.reactivex.Observable

abstract class DbDataSource<T>(private val cache: Cache<T>) : DataSource<T> {
    override fun list(): Observable<List<T>> = cache.get()
}