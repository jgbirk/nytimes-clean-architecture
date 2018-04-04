package com.nytimes.data.cache

import io.reactivex.Observable

interface Cache<T> {
    fun isExpired(): Observable<Boolean>
    fun isCached(): Observable<Boolean>
    fun get(): Observable<List<T>>
    fun put(list: List<T>)
    fun invalidate(): Observable<Unit>
}