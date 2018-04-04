package com.nytimes.domain.repository

import io.reactivex.Observable

interface Repository<T> {
    fun list(): Observable<List<T>>
}