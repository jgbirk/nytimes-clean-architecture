package com.nytimes.data.repository

import com.nytimes.data.BuildConfig
import com.nytimes.data.cache.Cache
import com.nytimes.data.model.StoryEntity
import com.nytimes.data.model.mapper.StoryMapper
import com.nytimes.data.net.ServiceFactory
import com.nytimes.data.repository.datasource.ApiDataSource
import io.reactivex.Observable

class StoryApiDataSource(cache: Cache<StoryEntity>): ApiDataSource<StoryEntity>(cache) {

    override fun list(): Observable<List<StoryEntity>> {
        return ServiceFactory.storyService.list(BuildConfig.API_KEY)
            .map { StoryMapper.transform(it) }
            .doOnNext { cache.put(it) }
    }
}