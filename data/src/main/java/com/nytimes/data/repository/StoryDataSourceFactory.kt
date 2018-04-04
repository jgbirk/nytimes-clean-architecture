package com.nytimes.data.repository

import com.nytimes.data.cache.Cache
import com.nytimes.data.model.StoryEntity
import com.nytimes.data.repository.datasource.DataSource
import com.nytimes.data.repository.datasource.DataSourceFactory
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class StoryDataSourceFactory(val cache: Cache<StoryEntity>): DataSourceFactory<StoryEntity>() {

    override fun create(): Observable<DataSource<StoryEntity>> {
        return Observable.zip(
            cache.isCached(),
            cache.isExpired(),
            BiFunction<Boolean, Boolean, Boolean> { cached, expired ->
                cached && !expired
            }).map {
                if (it) {
                    StoryDbDataSource(cache)
                } else {
                    StoryApiDataSource(cache)
                }
            }
    }
}