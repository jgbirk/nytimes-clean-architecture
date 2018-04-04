package com.nytimes.data.repository

import com.nytimes.data.model.mapper.StoryMapper
import com.nytimes.domain.model.Story
import com.nytimes.domain.repository.Repository
import io.reactivex.Observable

class StoryRepository(
    private val factory: StoryDataSourceFactory,
    private val mapper: StoryMapper) : Repository<Story> {

    override fun list(): Observable<List<Story>> =
        factory.create().flatMap { it.list() }.map { mapper.transform(it) }
}